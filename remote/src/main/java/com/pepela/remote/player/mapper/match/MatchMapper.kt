package com.pepela.remote.player.mapper.match

import com.pepela.data.match.model.Match
import com.pepela.remote.EntityMapper
import com.pepela.remote.player.model.match.MatchModel
import java.util.*

class MatchMapper(private val sideMapper: SideMapper) : EntityMapper<MatchModel, Match> {

    override fun from(model: MatchModel): Match =
            with(model) {
                Match(id, sideMapper.from(playerSlot), radiantWin, duration, heroId,
                        Date(startTime), kills, deaths, assists, xpPerMinute, goldPerMinute,
                        heroDamage, towerDamage, heroHealing, lastHits)
            }

}
