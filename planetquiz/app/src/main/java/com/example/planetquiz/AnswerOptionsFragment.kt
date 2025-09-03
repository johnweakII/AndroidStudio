package com.example.planetquiz

import android.os.Bundle
import android.view.View
import androidx.compose.material3.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.widget.Button

class AnswerOptionsFragment : Fragment(R.layout.fragment_answer_options) {

    private val correctAnswers = listOf("Jupiter", "Saturn", "Uranus")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val questionIndex = arguments?.getInt("questionIndex") ?: 0
        val navController = findNavController()

        val planetButtons = listOf(
            R.id.btnMercury to "Mercury",
            R.id.btnVenus to "Venus",
            R.id.btnEarth to "Earth",
            R.id.btnMars to "Mars",
            R.id.btnJupiter to "Jupiter",
            R.id.btnSaturn to "Saturn",
            R.id.btnUranus to "Uranus",
            R.id.btnNeptune to "Neptune"
        )

        planetButtons.forEach { (id, planet) ->
            view.findViewById<Button>(id).setOnClickListener {
                val isCorrect = planet == correctAnswers[questionIndex]
                val bundle = Bundle().apply {
                    putBoolean("isCorrect", isCorrect)
                    putInt("questionIndex", questionIndex)
                }
                navController.navigate(R.id.action_answerOptionsFragment_to_answerFragment, bundle)

            }
        }
    }
}

private fun Button.setOnClickListener(function: Any) {}
