package com.example.tddthreeapp.robots
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.tddthreeapp.R

class BaseRobot {
    fun enterNumber(number: String) = apply {
        onView(withId(R.id.etNumber)).perform(typeText(number), closeSoftKeyboard())
    }
    fun clickNext() = apply {
        onView(withId(R.id.btnGo)).perform(click())
    }
}