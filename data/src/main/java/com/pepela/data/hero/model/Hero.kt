package com.pepela.data.hero.model

data class Hero(val id: Long,
                val name: String,
                val localizedName: String,
                val primaryAttribute: Attribute,
                val attackType: AttackType,
                val roles: List<Role>,
                val legs: Int)
