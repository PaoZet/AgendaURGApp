package com.example.agendaar

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "AgendaURG.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE tareas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT)")
        db.execSQL("CREATE TABLE contactos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tareas")
        db.execSQL("DROP TABLE IF EXISTS contactos")
        onCreate(db)
    }

    fun insertarTarea(nombre: String, descripcion: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("descripcion", descripcion)
        }
        val resultado = db.insert("tareas", null, values)
        return resultado != -1L
    }

    fun obtenerTareas(): ArrayList<String> {
        val lista = ArrayList<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tareas", null)
        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(cursor.getColumnIndexOrThrow("nombre")))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun obtenerDescripcionTarea(nombreTarea: String): String {
        var desc = "Sin descripción"
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT descripcion FROM tareas WHERE nombre = ?", arrayOf(nombreTarea))
        if (cursor.moveToFirst()) {
            desc = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
        }
        cursor.close()
        return desc
    }

    fun insertarContacto(nombre: String, telefono: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("telefono", telefono)
        }
        val resultado = db.insert("contactos", null, values)
        return resultado != -1L
    }

    fun obtenerContactosConDetalle(): ArrayList<String> {
        val lista = ArrayList<String>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM contactos", null)
        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"))
                lista.add("$nombre\nTel: $telefono")
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }
}