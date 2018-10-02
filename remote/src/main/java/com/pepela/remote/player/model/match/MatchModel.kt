package com.pepela.remote.player.model.match

import com.google.gson.annotations.SerializedName
import java.util.*

class MatchModel(@SerializedName("match_id") val id: Long,
                 @SerializedName("radiant_win") val radiantWin: Boolean,
                 val duration: Long,
                 @SerializedName("hero_id") val heroId: Long,
                 @SerializedName("start_time") val startTime: Date,
                 val kills: Long,
                 val deaths: Long,
                 val assists: Long,
                 @SerializedName("xp_per_min") val xpPerMinute: Long,
                 @SerializedName("gold_per_min") val goldPerMinute: Long,
                 @SerializedName("hero_damage") val heroDamage: Long,
                 @SerializedName("tower_damage") val towerDamage: Long,
                 @SerializedName("hero_healing") val heroHealing: Long,
                 @SerializedName("last_hits") val lastHits: Long)
