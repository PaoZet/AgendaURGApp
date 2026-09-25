package com.example.agendaar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class TaskFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)
        val listView = view.findViewById<ListView>(R.id.listViewTareas)

        val dbHelper = DatabaseHelper(requireContext())
        val listaTareas = dbHelper.obtenerTareas()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listaTareas)
        listView.adapter = adapter

        // Intent Explícito con putExtra usando la descripción real de SQLite
        listView.setOnItemClickListener { _, _, position, _ ->
            val tareaSeleccionada = listaTareas[position]
            val descripcionReal = dbHelper.obtenerDescripcionTarea(tareaSeleccionada)

            sharedViewModel.selectedTask.value = tareaSeleccionada

            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("EXTRA_TITLE", tareaSeleccionada)
                putExtra("EXTRA_DESC", descripcionReal)
            }
            startActivity(intent)
        }

        return view
    }
}