package com.example.agendaar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        val listView = view.findViewById<ListView>(R.id.listViewContactos)

        val dbHelper = DatabaseHelper(requireContext())
        val listaContactos = dbHelper.obtenerContactosConDetalle()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listaContactos)
        listView.adapter = adapter

        // Se eliminó el setOnItemClickListener para que los contactos solo se muestren en la lista

        return view
    }
}