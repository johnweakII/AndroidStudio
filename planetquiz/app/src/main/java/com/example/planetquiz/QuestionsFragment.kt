package com.example.planetquiz

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class QuestionsFragment : Fragment(R.layout.fragment_questions) {
    private val questions = listOf(
        "What is the largest planet?",
        "Which planet has the most moons?",
        "Which planet spins on its side?"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        view.findViewById<Button>(R.id.btnQuestion1).setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("questionIndex", 0)
            navController.navigate(R.id.action_questionsFragment_to_answerOptionsFragment, bundle)
        }

        view.findViewById<Button>(R.id.btnQuestion2).setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("questionIndex", 1)
            navController.navigate(R.id.action_questionsFragment_to_answerOptionsFragment, bundle)
        }

        view.findViewById<Button>(R.id.btnQuestion3).setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("questionIndex", 2)
            navController.navigate(R.id.action_questionsFragment_to_answerOptionsFragment, bundle)
        }
    }
}