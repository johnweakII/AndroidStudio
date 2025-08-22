package com.labactivity1


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val redInput = findViewById<EditText>(R.id.redInput)
        val greenInput = findViewById<EditText>(R.id.greenInput)
        val blueInput = findViewById<EditText>(R.id.blueInput)
        val colorPanel = findViewById<TextView>(R.id.colorPanel)
        val button = findViewById<Button>(R.id.createColorBtn)

        button.setOnClickListener {
            val red = redInput.text.toString().trim().padStart(2, '0')
            val green = greenInput.text.toString().trim().padStart(2, '0')
            val blue = blueInput.text.toString().trim().padStart(2, '0')

            if (isValidHex(red) && isValidHex(green) && isValidHex(blue)) {
                val hexColor = "#${red.uppercase()}${green.uppercase()}${blue.uppercase()}"
                colorPanel.setBackgroundColor(Color.parseColor(hexColor))
            } else {
                Toast.makeText(this, "Enter valid hex values (0-9, A-F)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidHex(value: String): Boolean {
        return value.length == 2 && value.matches(Regex("^[0-9a-fA-F]{2}$"))
    }
}
