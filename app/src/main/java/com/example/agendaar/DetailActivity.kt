package com.example.agendaar

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val titulo = intent.getStringExtra("EXTRA_TITLE") ?: ""
        val desc = intent.getStringExtra("EXTRA_DESC") ?: ""

        findViewById<TextView>(R.id.tvTitle).text = titulo
        findViewById<TextView>(R.id.tvDesc).text = desc
    }
}