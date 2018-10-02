package com.pepela.remote.player.model.player

import com.google.gson.annotations.SerializedName


data class PlayerModel(@SerializedName("rank_tier") val rank: RankModel,
                       @SerializedName("leaderboard_rank") val leaderBoardRank: Long?,
                       val profile: ProfileModel)
