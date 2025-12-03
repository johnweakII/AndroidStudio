package com.example.tddthreeapp

import android.content.Context

open class TextProvider(private val context: Context) {
    fun getTextForButton(): String = context.getString(R.string.btn_label)
    // Add other text methods as required
}