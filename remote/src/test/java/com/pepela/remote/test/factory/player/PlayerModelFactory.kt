package com.pepela.remote.test.factory.player

import com.pepela.remote.player.model.player.PlayerModel
import com.pepela.remote.test.factory.player.ProfileModelFactory.Factory.makeProfileModel
import com.pepela.remote.test.factory.player.RankModelFactory.Factory.randomRankModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong

class PlayerModelFactory {

    companion object Factory {

        fun makePlayerModel() = PlayerModel(randomRankModel(), randomLong(), makeProfileModel())

    }

}
