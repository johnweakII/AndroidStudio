package com.example.tddthreeapp

class UiTestApplication : MyApplication() {
    // Espresso handles IdlingResource via the singleton in MyApplication,
    // but we can override if specific behavior is needed.
}