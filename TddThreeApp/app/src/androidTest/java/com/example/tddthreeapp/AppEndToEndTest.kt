package com.example.tddthreeapp

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
// --- Fix: Ensure both robot imports are present and correct ---
import com.example.tddthreeapp.robots.MainRobot
import com.example.tddthreeapp.robots.ListRobot
import com.example.tddthreeapp.robots.ResultRobot
// -----------------------------------------------------------
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
class AppEndToEndTest {
    // ... setup code (Rule, Before, After) ...
    // (Ensure you have implemented ThirdActivity and the logic correctly)

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as MyApplication
        IdlingRegistry.getInstance().register(app.idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as MyApplication
        IdlingRegistry.getInstance().unregister(app.idlingResource)
    }

    @Test
    fun fullAppFlow() {
        // 1. Activity 1 interaction
        MainRobot()
            .enterNumber("3")
            .clickNext()

        // 2. Activity 2 interaction (List loads asynchronously, IdlingResource waits)
        ListRobot()
            .clickItem(1) // Clicks the item at index 1, which corresponds to "Item 2"

        // 3. Activity 3 assertion
        ResultRobot()
            .verifyText("You clicked Item 2")
    }
}