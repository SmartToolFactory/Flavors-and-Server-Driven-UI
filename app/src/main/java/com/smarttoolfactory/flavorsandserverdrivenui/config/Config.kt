package com.smarttoolfactory.flavorsandserverdrivenui.config

import com.google.gson.annotations.SerializedName

data class Config(
    @SerializedName("version") val version: Int,
    @SerializedName("logo") val logo: String,
    @SerializedName("navigation_type") var navigationType: Int = NAVIGATION_BOTTOM_NAV,
    @SerializedName("colors") val colors: Colors,
    @SerializedName("features") val features: Features
)

const val NAVIGATION_NONE = 0
const val NAVIGATION_TOOLBAR = 1
const val NAVIGATION_BOTTOM_NAV = 2
const val NAVIGATION_NAV_VIEW = 3


val DEFAULT_CONFIG = Config(1, "", NAVIGATION_TOOLBAR, Colors(), Features())