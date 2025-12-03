package com.example.tddthreeapp
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListItem(val text: String) : Parcelable