package com.example.tddthreeapp.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.tddthreeapp.R

class ResultRobot {
    fun verifyText(expectedText: String) = apply {
        // Assert that the TextView in Activity 3 displays the correct text
        onView(withId(R.id.tvResult))
            .check(matches(withText(expectedText)))
    }
}