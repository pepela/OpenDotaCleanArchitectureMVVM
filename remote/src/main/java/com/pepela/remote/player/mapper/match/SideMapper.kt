package com.pepela.remote.player.mapper.match

import com.pepela.data.match.model.Side
import com.pepela.remote.EntityMapper
import com.pepela.remote.player.model.match.SideModel

open class SideMapper : EntityMapper<SideModel, Side> {

    override fun from(model: SideModel): Side {
        return when (model) {
            SideModel.DIRE -> Side.DIRE
            SideModel.RADIANT -> Side.RADIANT
        }
    }

}
