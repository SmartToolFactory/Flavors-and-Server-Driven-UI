package com.smarttoolfactory.flavorsandserverdrivenui.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.smarttoolfactory.flavorsandserverdrivenui.MyApplication
import com.smarttoolfactory.flavorsandserverdrivenui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val configManager = MyApplication.configManager

        val colorMap = configManager.colorMap

        colorMap["splashBackground"]?.let {
            view.findViewById<View>(R.id.containerSplash).setBackgroundColor(it)
        }

        colorMap["textColor"]?.let {
            view.findViewById<TextView>(R.id.tvSplash).setTextColor(it)
        }

        val ivSplash = view.findViewById<ImageView>(R.id.ivSplash)

        Glide.with(this)
            .load(configManager.config?.logo)
            .error(R.drawable.splash)
            .into(ivSplash)

        val goToLogin = configManager.config?.features?.featureRequireLogin ?: false

        lifecycleScope.launch(Dispatchers.Main) {
            delay(2000)
            if (goToLogin) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }
    }
}
