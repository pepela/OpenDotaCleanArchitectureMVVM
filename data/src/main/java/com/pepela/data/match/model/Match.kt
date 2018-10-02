package com.pepela.data.match.model

import java.util.*

data class Match(val id: Long,
                 val radiantWin: Boolean,
                 val duration: Long,
                 val heroId: Long,
                 val startTime: Date,
                 val kills: Long,
                 val deaths: Long,
                 val assists: Long,
                 val xpPerMinute: Long,
                 val goldPerMinute: Long,
                 val heroDamage: Long,
                 val towerDamage: Long,
                 val heroHealing: Long,
                 val lastHits: Long)
