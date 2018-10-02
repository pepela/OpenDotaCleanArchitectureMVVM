package com.pepela.remote.test.factory.player

import com.pepela.remote.player.model.player.RankModel

class RankModelFactory {

    companion object Factory {

        fun randomRankModel() = RankModel.values().toList().shuffled().first()

    }

}
