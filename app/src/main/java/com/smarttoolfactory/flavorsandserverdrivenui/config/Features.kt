package com.smarttoolfactory.flavorsandserverdrivenui.config

import com.google.gson.annotations.SerializedName


data class Features(
	@SerializedName("feature_require_login") val featureRequireLogin: Boolean,
	@SerializedName("feature_chat") val featureChat: Boolean,
	@SerializedName("feature_contacts") val featureContacts: Boolean,
	@SerializedName("feature_call") val featureCall: Boolean,
	@SerializedName("feature_call_dial_pad") val featureCallDialPad: Boolean,
	@SerializedName("feature_conference") val featureConference: Boolean,
	@SerializedName("feature_settings") val featureSettings: Boolean
)