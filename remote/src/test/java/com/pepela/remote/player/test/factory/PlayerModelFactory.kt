package com.pepela.remote.player.test.factory

import com.pepela.remote.player.model.PlayerModel
import com.pepela.remote.player.test.factory.ProfileModelFactory.Factory.makeProfileModel
import com.pepela.remote.player.test.factory.RankModelFactory.Factory.randomRankModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong

class PlayerModelFactory {

    companion object Factory {

        fun makePlayerModel() = PlayerModel(randomRankModel(), randomLong(), makeProfileModel())

    }

}
