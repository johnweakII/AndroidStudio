package com.example.sportsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class MySportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_sports, container, false)

        val btnFootball: Button = view.findViewById(R.id.btnFootball)
        val btnBasketball: Button = view.findViewById(R.id.btnBasketball)

        btnFootball.setOnClickListener {
            findNavController().navigate(R.id.action_mySports_to_footballFragment)
        }

        btnBasketball.setOnClickListener {
            findNavController().navigate(R.id.action_mySports_to_basketballFragment)
        }

        return view
    }
}
