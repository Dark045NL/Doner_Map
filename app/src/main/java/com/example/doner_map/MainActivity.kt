package com.example.doner_map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            // Define the action to perform when the button is clicked.
            // For example, you can start another activity.
            val intent = Intent(this, MainActivityMap::class.java)
            startActivity(intent)
        }

        val Ivan = findViewById<Button>(R.id.Ivan)

        Ivan.setOnClickListener {
            // Define the action to perform when the button is clicked.
            // For example, you can start another activity.
            val intent = Intent(this, Smaakprofiel::class.java)
            startActivity(intent)
        }
    }
}