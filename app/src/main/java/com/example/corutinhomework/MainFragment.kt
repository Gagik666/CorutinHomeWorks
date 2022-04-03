package com.example.corutinhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAppleTask = view.findViewById<Button>(R.id.btnAppleTask)
        val btnWatchTask = view.findViewById<Button>(R.id.btnWatchTask)

        btnAppleTask.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_appleInputFragment)
        }

        btnWatchTask.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_watchFragment)
        }

    }

}