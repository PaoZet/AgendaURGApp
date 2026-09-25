package com.example.agendaar

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var dbHelper: DatabaseHelper
    private var currentTab = 1 // 1 para Tareas, 2 para Contactos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Borra base de datos para no ocupar espacio
        if (savedInstanceState == null) {
            deleteDatabase("AgendaURG.db")
        }

        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        dbHelper = DatabaseHelper(this)

        if (savedInstanceState == null) {
            loadFragment(TaskFragment())
        }

        findViewById<android.widget.Button>(R.id.btnFragment1).setOnClickListener {
            currentTab = 1
            loadFragment(TaskFragment())
        }

        findViewById<android.widget.Button>(R.id.btnFragment2).setOnClickListener {
            currentTab = 2
            loadFragment(ContactFragment())
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            mostrarDialogoDinamico(view)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun mostrarDialogoDinamico(view: android.view.View) {
        val builder = AlertDialog.Builder(this)

        if (currentTab == 1) {
            // Diálogo para Tareas (Nombre y Descripción)
            builder.setTitle("Agregar Nueva Tarea")
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(40, 20, 40, 20)
            }
            val inputNombre = EditText(this).apply { hint = "Nombre de la tarea" }
            val inputDesc = EditText(this).apply { hint = "Descripción" }
            layout.addView(inputNombre)
            layout.addView(inputDesc)
            builder.setView(layout)

            builder.setPositiveButton("Guardar") { _, _ ->
                val nombre = inputNombre.text.toString().trim()
                val desc = inputDesc.text.toString().trim()
                if (nombre.isNotEmpty()) {
                    dbHelper.insertarTarea(nombre, desc)
                    Snackbar.make(view, "Tarea guardada con éxito", Snackbar.LENGTH_SHORT).show()
                    loadFragment(TaskFragment())
                }
            }
        } else {
            // Diálogo para Contactos
            builder.setTitle("Agregar Nuevo Contacto")
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(40, 20, 40, 20)
            }
            val inputNombre = EditText(this).apply { hint = "Nombre del contacto" }
            val inputTel = EditText(this).apply {
                hint = "Teléfono"
                inputType = android.text.InputType.TYPE_CLASS_PHONE
            }
            layout.addView(inputNombre)
            layout.addView(inputTel)
            builder.setView(layout)

            builder.setPositiveButton("Guardar") { _, _ ->
                val nombre = inputNombre.text.toString().trim()
                val tel = inputTel.text.toString().trim()
                if (nombre.isNotEmpty() && tel.isNotEmpty()) {
                    dbHelper.insertarContacto(nombre, tel)
                    Snackbar.make(view, "Contacto guardado con éxito", Snackbar.LENGTH_SHORT).show()
                    loadFragment(ContactFragment())
                }
            }
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }
}