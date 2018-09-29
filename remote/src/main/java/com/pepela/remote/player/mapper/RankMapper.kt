package com.pepela.remote.player.mapper

import com.pepela.data.player.model.Rank
import com.pepela.remote.EntityMapper
import com.pepela.remote.player.model.RankModel

open class RankMapper : EntityMapper<RankModel, Rank> {

    override fun from(model: RankModel): Rank {
        return when (model) {
            RankModel.HERALD -> Rank.HERALD
            RankModel.GUARDIAN -> Rank.GUARDIAN
            RankModel.CRUSADER -> Rank.CRUSADER
            RankModel.ARCHON -> Rank.ARCHON
            RankModel.LEGEND -> Rank.LEGEND
            RankModel.ANCIENT -> Rank.ARCHON
            RankModel.DIVINE -> Rank.DIVINE
            RankModel.IMMORTAL -> Rank.IMMORTAL
        }
    }

}
