package com.pepela.remote.hero.model

import com.google.gson.annotations.SerializedName

enum class RoleModel {

    @SerializedName("Carry")
    CARRY,
    @SerializedName("Escape")
    ESCAPE,
    @SerializedName("Nuker")
    NUKER,
    @SerializedName("Initiator")
    INITIATOR,
    @SerializedName("Disabler")
    DISABLER,
    @SerializedName("Support")
    SUPPORT,
    @SerializedName("Jungler")
    JUNGLER,
    @SerializedName("Pusher")
    PUSHER,
    @SerializedName("Durable")
    DURABLE

}
