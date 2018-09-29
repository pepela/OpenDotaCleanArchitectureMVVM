package com.pepela.remote.player.mapper

import com.pepela.data.player.model.Player
import com.pepela.remote.EntityMapper
import com.pepela.remote.player.model.PlayerModel

open class PlayerMapper(private val rankMapper: RankMapper, private val profileMapper: ProfileMapper)
    : EntityMapper<PlayerModel, Player> {

    override fun from(model: PlayerModel): Player =
            with(model) {
                Player(rankMapper.from(rank), leaderBoardRank, profileMapper.from(profile))
            }

}
