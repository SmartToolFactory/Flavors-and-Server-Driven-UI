package com.smarttoolfactory.flavorsandserverdrivenui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.smarttoolfactory.flavorsandserverdrivenui.MyApplication
import com.smarttoolfactory.flavorsandserverdrivenui.R

class CallsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_calls, container, false)

        val colorMap = MyApplication.configManager.colorMap

        colorMap["callsBackground"]?.let {
            view.rootView.setBackgroundColor(it)
        }

        colorMap["textColor"]?.let {
            view.findViewById<TextView>(R.id.tvCalls).setTextColor(it)
        }

        return view
    }

}