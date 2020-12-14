package com.smarttoolfactory.flavorsandserverdrivenui.config

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.toColorInt
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smarttoolfactory.flavorsandserverdrivenui.R
import org.json.JSONException
import org.json.JSONObject

/**
 * Color, theme, style management class based on config file retrieved from remote data source
 *
 * * In this sample we are using assets/config/config.json for simplicity
 */
class ConfigManager(private val context: Context) {

    var config: Config? = null

    val colorMap by lazy {
        HashMap<String, Int>()
    }

    fun parseConfigFile() {

        val resource = context
            .applicationContext
            .assets
            .readAssetsFile("config/config.json")

        config = convertToObjectsFromString<Config>(resource)

        config?.colors?.let {
            parseColors(it)
        }

        println()
    }

    // TODO Parse colors with Efficient way, this is temporary
    private fun parseColors(colors: Colors) {
        config?.run {

            /*
                    "colorPrimaryDark": "#FF6200EE",
                    "colorPrimary": "#FF3700B3",
                    "colorAccent": "#FF03DAC5",
                    "splash_background": "#9CCC65",
                    "main_background": "#DCEDC8",
                    "text_color": "FF000000",
                    "chats_background": "#757575",
                    "contacts_background": "#757575",
                    "calls_background": "#757575",
                    "nav_tab_icon_color": "#FFD54F",
                    "nav_tab_icon_color_selected": "#F5F5F5"
             */

            colorMap["colorPrimaryDark"] = try {
                colors.colorPrimaryDark.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null)
            }

            colorMap["colorPrimary"] = try {
                colors.colorPrimary.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
            }

            colorMap["colorAccent"] = try {
                colors.colorAccent.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.colorAccent, null)
            }


            colorMap["splashBackground"] = try {
                colors.splashBackground.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.splash_background, null)
            }

            colorMap["loginBackground"] = try {
                colors.loginBackground.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.login_background, null)
            }

            colorMap["textColor"] = try {
                colors.textColor.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.text_color, null)
            }

            colorMap["chatsBackground"] = try {
                colors.chatsBackground.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.chats_background, null)
            }

            colorMap["contactsBackground"] = try {
                colors.contactsBackground.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.contacts_background, null)
            }

            colorMap["callsBackground"] = try {
                colors.callsBackground.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.calls_background, null)
            }

            colorMap["navTabIconColor"] = try {
                colors.navTabIconColor.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.nav_tab_icon_color, null)
            }

            colorMap["navTabIconColorSelected"] = try {
                colors.navTabIconColorSelected.toColorInt()
            } catch (e: Exception) {
                ResourcesCompat.getColor(
                    context.resources,
                    R.color.nav_tab_icon_color_selected,
                    null
                )
            }
        }

    }
}


inline fun <reified T> convertToObjectsFromString(input: String): T? {
    return Gson().fromJsonWithType<T>(input)
}

inline fun <reified T> Gson.fromJsonWithType(json: String): T? =
    fromJson<T>(json, object : TypeToken<T>() {}.type)


private fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }
