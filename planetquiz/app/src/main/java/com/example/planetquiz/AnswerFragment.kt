package com.example.planetquiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.TextView


class AnswerFragment : Fragment(R.layout.fragment_answer) {

    private val explanations = listOf(
        "Jupiter is the largest planet and is 2.5 times the mass of all the other planets put together.",
        "Saturn has the most moons and has 82 moons.",
        "Uranus spins on its side with its axis at nearly a right angle to the Sun."
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val isCorrect = arguments?.getBoolean("isCorrect") ?: false
        val questionIndex = arguments?.getInt("questionIndex") ?: 0

        view.findViewById<TextView>(R.id.txtResult).text =
            if (isCorrect) getString(R.string.correct) else getString(R.string.incorrect)

        view.findViewById<TextView>(R.id.txtExplanation).text =
            explanations[questionIndex]
    }
}
