package com.smarttoolfactory.flavorsandserverdrivenui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val bottomContainer = rootView.findViewById<ConstraintLayout>(R.id.containerBottom)

        val bottomNavigationView =
            inflater.inflate(R.layout.layout_bottom_navigation_view, container, false)

        bottomContainer.addView(bottomNavigationView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "FREE Fragment", Toast.LENGTH_SHORT).show()
    }
}