package com.smarttoolfactory.flavorsandserverdrivenui.config

import com.google.gson.annotations.SerializedName


data class Colors(

    @SerializedName("colorPrimaryDark") val colorPrimaryDark: String = "",
    @SerializedName("colorPrimary") val colorPrimary: String = "",
    @SerializedName("colorAccent") val colorAccent: String = "",
    @SerializedName("background") val background: String = "",
    @SerializedName("splash_background") val splashBackground: String = "",
    @SerializedName("login_background") val loginBackground: String = "",
    @SerializedName("text_color") val textColor: String = "",
    @SerializedName("chats_background") val chatsBackground: String = "",
    @SerializedName("contacts_background") val contactsBackground: String = "",
    @SerializedName("calls_background") val callsBackground: String = "",
    @SerializedName("nav_tab_icon_color") val navTabIconColor: String = "",
    @SerializedName("nav_tab_icon_color_selected") val navTabIconColorSelected: String = ""
)