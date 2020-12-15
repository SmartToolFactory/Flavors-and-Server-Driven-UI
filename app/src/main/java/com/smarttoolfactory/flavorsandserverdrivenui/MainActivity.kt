package com.smarttoolfactory.flavorsandserverdrivenui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyApplication.configManager.colorMap["colorPrimaryDark"]?.let {
            window.statusBarColor = it
        }
    }
}
