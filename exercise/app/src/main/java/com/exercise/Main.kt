package com.exercise

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper



class Main : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main) // Use your layout name here

        // Auto-transition to SecondActivity after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            finish() // Optional: prevents going back to MainActivity
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            ) // Optional animation
        }, 5000)
    }
}