package com.Login

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val correctUsername = "admin"
    private val correctPassword = "password123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)
        val welcomeMessage = findViewById<TextView>(R.id.welcome_message)
        val loginPrompt = findViewById<TextView>(R.id.login_prompt)

        loginButton.setOnClickListener {
            val enteredUsername = usernameEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            } else if (enteredUsername == correctUsername && enteredPassword == correctPassword) {

                val welcomeText = "Welcome, $enteredUsername!"
                welcomeMessage.text = welcomeText
                welcomeMessage.visibility = View.VISIBLE


                usernameEditText.visibility = View.GONE
                passwordEditText.visibility = View.GONE
                loginButton.visibility = View.GONE
                loginPrompt.visibility = View.GONE
            } else {

                Toast.makeText(this, "Incorrect username or password.", Toast.LENGTH_SHORT).show()
                usernameEditText.text.clear()
                passwordEditText.text.clear()
            }
        }
    }
}
