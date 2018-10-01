package com.pepela.remote.player.model

import com.google.gson.annotations.SerializedName

enum class RankModel {
    @SerializedName("1")
    HERALD,
    @SerializedName("20")
    GUARDIAN,
    @SerializedName("30")
    CRUSADER,
    @SerializedName("40")
    ARCHON,
    @SerializedName("50")
    LEGEND,
    @SerializedName("62")
    ANCIENT,
    @SerializedName("70")
    DIVINE,
    @SerializedName("80")
    IMMORTAL
}
