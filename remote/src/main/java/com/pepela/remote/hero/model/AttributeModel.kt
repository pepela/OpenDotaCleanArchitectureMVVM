package com.pepela.remote.hero.model

import com.google.gson.annotations.SerializedName

enum class AttributeModel {

    @SerializedName("agi")
    AGILITY,
    @SerializedName("str")
    STRENGTH,
    @SerializedName("int")
    INTELLIGENCE

}
