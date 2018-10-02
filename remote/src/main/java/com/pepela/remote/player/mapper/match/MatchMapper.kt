package com.pepela.remote.player.mapper.match

import com.pepela.data.match.model.Match
import com.pepela.remote.EntityMapper
import com.pepela.remote.player.model.match.MatchModel

class MatchMapper : EntityMapper<MatchModel, Match> {

    override fun from(model: MatchModel): Match =
            with(model) {
                Match(id, radiantWin, duration, heroId, startTime, kills, deaths, assists,
                        xpPerMinute, goldPerMinute, heroDamage, towerDamage, heroHealing, lastHits)
            }

}
