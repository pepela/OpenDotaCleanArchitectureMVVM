package com.pepela.opendota.test.factory

import com.pepela.data.player.model.Rank

class RankFactory {

    companion object Factory{

        fun randomRank() = Rank.values().toList().shuffled().first()

    }

}
