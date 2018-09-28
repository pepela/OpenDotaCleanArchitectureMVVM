package com.pepela.data.test.factory

import com.pepela.data.player.model.Rank

class RankFactory {

    companion object {

        fun randomRank() = Rank.values().toList().shuffled().first()

    }

}
