package com.pepela.data.test.factory

import com.pepela.data.player.model.Player
import com.pepela.data.test.factory.DataFactory.Factory.randomLong
import com.pepela.data.test.factory.ProfileFactory.Factory.makeProfile
import com.pepela.data.test.factory.RankFactory.Companion.randomRank

class PlayerFactory {

    companion object Factory {

        fun makePlayer() = Player(randomRank(), randomLong(), makeProfile())

        fun makePlayers(count: Int) = MutableList(count) { makePlayer() }
    }

}
