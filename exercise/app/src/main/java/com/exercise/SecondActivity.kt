package com.exercise

import android.os.Bundle
import android.app.Activity
import android.widget.*

class SecondActivity : Activity() {
    lateinit var etNumber1: EditText
    lateinit var etNumber2: EditText
    lateinit var tvResult: TextView
    lateinit var btnAdd: Button
    lateinit var btnSubtract: Button
    lateinit var btnMultiply: Button
    lateinit var btnDivide: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.page2)

        etNumber1 = findViewById(R.id.etNumber1)
        etNumber2 = findViewById(R.id.etNumber2)
        tvResult = findViewById(R.id.tvResult)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)


        btnAdd.setOnClickListener { calculate('+') }
        btnSubtract.setOnClickListener { calculate('-') }
        btnMultiply.setOnClickListener { calculate('*') }
        btnDivide.setOnClickListener { calculate('/') }
    }
    private fun calculate(operator: Char) {
        val num1Text = etNumber1.text.toString()
        val num2Text = etNumber2.text.toString()

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            tvResult.text = "Please enter both numbers."
            return
        }

        val num1 = num1Text.toDouble()
        val num2 = num2Text.toDouble()
        val result = when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> if (num2 != 0.0) num1 / num2 else {
                tvResult.text = "Cannot divide by zero."
                return
            }
            else -> 0.0
        }

        tvResult.text = "Result: $result"
    }
}