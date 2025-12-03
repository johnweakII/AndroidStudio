package com.example.tddthreeapp

import android.content.Intent // Required for Intent
import android.os.Bundle
import android.widget.Button // Use the standard Android Button
import android.widget.EditText // Use the standard Android EditText
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnGo)
        val et = findViewById<EditText>(R.id.etNumber)

        // Use TextProvider
        val app = application as MyApplication
        btn.text = app.textProvider.getTextForButton()

        btn.setOnClickListener {
            val count = et.text.toString().toIntOrNull() ?: 0
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("COUNT", count)
            startActivity(intent)
        }
    }
}