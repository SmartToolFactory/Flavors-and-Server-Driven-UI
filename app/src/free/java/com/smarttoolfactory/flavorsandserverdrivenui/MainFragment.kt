package com.smarttoolfactory.flavorsandserverdrivenui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarttoolfactory.flavorsandserverdrivenui.ui.sceen.MainScreen

class MainFragment : Fragment() {

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