package com.smarttoolfactory.flavorsandserverdrivenui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFade
import com.smarttoolfactory.flavorsandserverdrivenui.ui.screen.MainScreen

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialFade()
            .apply {
                duration = 500
            }

        reenterTransition = MaterialFade()
            .apply {
                duration = 500
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return MainScreen(
            childFragmentManager,
            viewLifecycleOwner.lifecycle,
            inflater,
            container,
            requireContext()
        )
            .getMainScreenView()
    }

}