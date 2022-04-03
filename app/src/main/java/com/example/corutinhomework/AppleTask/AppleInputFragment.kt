package com.example.corutinhomework.AppleTask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.corutinhomework.R

class AppleInputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apple_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnStart = view.findViewById<Button>(R.id.btnStart)
        val edNumber = view.findViewById<EditText>(R.id.edNumber)
        val edMaxNumber = view.findViewById<EditText>(R.id.edMaxNumber)

            btnStart.setOnClickListener {
                if (edNumber.text.isEmpty() || edNumber.text.toString().toInt() > 40) {
                    edNumber.error = ("The number should not exceed 40")
                } else if (edMaxNumber.text.isEmpty() || edMaxNumber.text.toString().toInt() > 50) {
                    edMaxNumber.error = ("The number should not exceed 50")
                } else {
                    findNavController().navigate(AppleInputFragmentDirections.actionAppleInputFragmentToAppleResultFragment(
                        edNumber.text.toString().toInt(), edMaxNumber.text.toString().toInt()
                    ))
                }
            }
    }


}