package com.smarttoolfactory.flavorsandserverdrivenui

import android.app.Application
import com.smarttoolfactory.flavorsandserverdrivenui.config.ConfigManager

class MyApplication : Application() {

    companion object {
        lateinit var configManager: ConfigManager
    }

    override fun onCreate() {
        super.onCreate()
        configManager = ConfigManager(this)

        configManager.parseConfigFile()
    }

}
