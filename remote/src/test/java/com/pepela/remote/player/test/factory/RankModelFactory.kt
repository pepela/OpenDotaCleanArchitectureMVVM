package com.pepela.remote.player.test.factory

import com.pepela.remote.player.model.RankModel

class RankModelFactory {

    companion object Factory {

        fun randomRankModel() = RankModel.values().toList().shuffled().first()

    }

}
