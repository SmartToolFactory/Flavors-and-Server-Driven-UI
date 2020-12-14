package com.smarttoolfactory.flavorsandserverdrivenui.config

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
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
                    "background": "#FFFFFFFF",
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
                Color.parseColor(colors.colorPrimaryDark)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null)
            }

            colorMap["colorPrimary"] = try {
                Color.parseColor(colors.colorPrimary)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
            }

            colorMap["colorAccent"] = try {
                Color.parseColor(colors.colorAccent)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.colorAccent, null)
            }

            colorMap["background"] = try {
                Color.parseColor(colors.background)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.background, null)
            }

            colorMap["splashBackground"] = try {
                Color.parseColor(colors.splashBackground)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.splash_background, null)
            }

            colorMap["loginBackground"] = try {
                Color.parseColor(colors.loginBackground)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.login_background, null)
            }

            colorMap["textColor"] = try {
                Color.parseColor(colors.textColor)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.text_color, null)
            }

            colorMap["chatsBackground"] = try {
                Color.parseColor(colors.chatsBackground)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.chats_background, null)
            }

            colorMap["contactsBackground"] = try {
                Color.parseColor(colors.contactsBackground)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.contacts_background, null)
            }

            colorMap["callsBackground"] = try {
                Color.parseColor(colors.callsBackground)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.calls_background, null)
            }

            colorMap["navTabIconColor"] = try {
                Color.parseColor(colors.navTabIconColor)
            } catch (e: Exception) {
                ResourcesCompat.getColor(context.resources, R.color.nav_tab_icon_color, null)
            }

            colorMap["navTabIconColorSelected"] = try {
                Color.parseColor(colors.navTabIconColorSelected)
            } catch (e: Exception) {
                ResourcesCompat.getColor(
                    context.resources,
                    R.color.nav_tab_icon_color_selected,
                    null
                )
            }
        }

    }


    @Throws(JSONException::class)
    fun jsonToMap(t: String?) {
        val map = HashMap<String, String>()
        val jObject = JSONObject(t)
        val keys: Iterator<*> = jObject.keys()
        while (keys.hasNext()) {
            val key = keys.next() as String
            val value = jObject.getString(key)
            map[key] = value
        }
        println("json : $jObject")
        println("map : $map")
    }
}


inline fun <reified T> convertToObjectsFromString(input: String): T? {
    return Gson().fromJsonWithType<T>(input)
}

inline fun <reified T> Gson.fromJsonWithType(json: String): T? =
    fromJson<T>(json, object : TypeToken<T>() {}.type)


private fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }
