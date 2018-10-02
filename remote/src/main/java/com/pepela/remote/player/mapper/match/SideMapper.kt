package com.pepela.remote.player.mapper.match

import com.pepela.data.match.model.Side
import com.pepela.remote.EntityMapper

class SideMapper : EntityMapper<Long, Side> {

    override fun from(model: Long): Side {
        return if (model in 0..4) {
            Side.RADIANT
        } else {
            Side.DIRE
        }

    }

}
