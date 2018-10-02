package com.pepela.remote.player.model.player

import com.google.gson.annotations.SerializedName

data class ProfileModel(@SerializedName("personaname") val name: String,
                        @SerializedName("account_id") val accountId: Long,
                        @SerializedName("avatarfull") val avatar: String,
                        @SerializedName("profileurl") val steamProfileUrl: String)
