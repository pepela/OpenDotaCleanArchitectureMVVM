package com.pepela.remote.player.model.player

import com.google.gson.annotations.SerializedName
import java.util.*

data class SearchProfileModel(@SerializedName("personaname") val name: String,
                              @SerializedName("account_id") val accountId: Long,
                              @SerializedName("avatarfull") val avatar: String,
                              @SerializedName("last_match_time") val lastPlayed: Date?)
