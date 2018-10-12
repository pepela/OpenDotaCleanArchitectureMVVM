package com.pepela.remote.hero.model

import com.google.gson.annotations.SerializedName

data class HeroModel(val id: Long,
                     val name: String,
                     @SerializedName("localized_name") val localizedName: String,
                     @SerializedName("primary_attr") val primaryAttribute: AttributeModel,
                     @SerializedName("attack_type") val attackType: AttackTypeModel,
                     val roles: List<RoleModel>,
                     val legs: Int)
