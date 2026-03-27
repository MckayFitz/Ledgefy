package com.example.ledgefy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.ledgefy.R

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val closeButton = view.findViewById<Button>(R.id.btn_close_settings)
        closeButton.setOnClickListener {
            activity?.findViewById<View>(R.id.settings_fragment_container)?.visibility = View.GONE
        }

        return view
    }
}
