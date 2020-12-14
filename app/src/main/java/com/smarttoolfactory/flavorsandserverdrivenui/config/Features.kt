package com.smarttoolfactory.flavorsandserverdrivenui.config

import com.google.gson.annotations.SerializedName


data class Features(
	@SerializedName("feature_require_login") val featureRequireLogin: Boolean = false,
	@SerializedName("feature_chat") val featureChat: Boolean = true,
	@SerializedName("feature_contacts") val featureContacts: Boolean = true,
	@SerializedName("feature_call") val featureCall: Boolean = true,
	@SerializedName("feature_call_dial_pad") val featureCallDialPad: Boolean = true,
	@SerializedName("feature_conference") val featureConference: Boolean = true,
	@SerializedName("feature_settings") val featureSettings: Boolean = true
)