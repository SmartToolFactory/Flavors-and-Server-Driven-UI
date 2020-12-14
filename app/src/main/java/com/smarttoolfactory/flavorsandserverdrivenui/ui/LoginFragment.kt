package com.smarttoolfactory.flavorsandserverdrivenui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smarttoolfactory.flavorsandserverdrivenui.MyApplication
import com.smarttoolfactory.flavorsandserverdrivenui.R

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val colorMap = MyApplication.configManager.colorMap

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        colorMap["loginFragment"]?.let {
            view.rootView.setBackgroundColor(it)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

}