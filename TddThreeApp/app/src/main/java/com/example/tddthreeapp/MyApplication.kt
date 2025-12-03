package com.example.tddthreeapp

import android.app.Application
import androidx.test.espresso.idling.CountingIdlingResource

open class MyApplication : Application() {
    lateinit var textProvider: TextProvider
    lateinit var listLoader: ListLoader

    // Used for UI Testing synchronization
    val idlingResource = CountingIdlingResource("Loader")

    override fun onCreate() {
        super.onCreate()
        textProvider = TextProvider(this)
        listLoader = createListLoader()
    }

    open fun createListLoader(): ListLoader {
        return ListLoader(idlingResource)
    }
}