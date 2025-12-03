package com.example.tddthreeapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val itemText = intent.getStringExtra("ITEM_TEXT")
        val textView = findViewById<TextView>(R.id.tvResult)

        // Resolve error: Unresolved reference 'text' (The ListItem property)
        // Resolve error: Unresolved reference 'putExtra' (Used in SecondActivity)
        if (itemText != null) {
            textView.text = "You clicked $itemText"
        } else {
            textView.text = "Error: No item clicked."
        }
    }
}