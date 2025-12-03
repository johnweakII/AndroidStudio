package com.example.tddthreeapp

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
// --- CORE ESPRESSO IMPORTS ---
import androidx.test.espresso.Espresso.onView               // Resolves onView
import androidx.test.espresso.action.ViewActions.* // Resolves typeText, closeSoftKeyboard, click
import androidx.test.espresso.matcher.ViewMatchers.withId // Resolves withId
// -----------------------------
import androidx.test.espresso.intent.Intents              // Resolves Intents (class)
import androidx.test.espresso.intent.Intents.intended     // Resolves intended (static method)
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent // Resolves hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = IntegrationTestApplication::class) // Use the immediate loader
@MediumTest
class MainActivityIntegrationTest {

    @Test
    fun testNavigationToSecondActivity() {
        Intents.init() // Unresolved reference 'Intents' (class)
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        // Unresolved reference 'onView', 'withId', 'typeText', 'closeSoftKeyboard'
        onView(withId(R.id.etNumber)).perform(typeText("5"), closeSoftKeyboard())

        // Unresolved reference 'onView', 'withId', 'click'
        onView(withId(R.id.btnGo)).perform(click())

        // Unresolved reference 'intended', 'hasComponent'
        intended(hasComponent(SecondActivity::class.java.name))

        Intents.release() // Unresolved reference 'Intents' (class)
    }
}