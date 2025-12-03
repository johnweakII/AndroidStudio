package com.example.tddthreeapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        // Use UiTestApplication for Instrumented tests
        return super.newApplication(cl, UiTestApplication::class.java.name, context)
    }
}