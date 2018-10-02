package com.pepela.opendota.test.factory

import com.pepela.data.player.model.Player
import com.pepela.data.test.factory.player.RankFactory.Companion.randomRank
import com.pepela.opendota.test.factory.DataFactory.Factory.randomLong
import com.pepela.opendota.test.factory.ProfileFactory.Factory.makeProfile

class PlayerFactory {

    companion object Factory {

        fun makePlayer() = Player(randomRank(), randomLong(), makeProfile())

    }

}
