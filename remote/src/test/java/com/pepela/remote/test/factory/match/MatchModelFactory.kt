package com.pepela.remote.test.factory.match

import com.pepela.remote.player.model.match.MatchModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomBoolean
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong
import com.pepela.remote.test.factory.match.SideModelFactory.Factory.randomSideModel

class MatchModelFactory {

    companion object Factory {

        fun makeMatchModel(): MatchModel =
                MatchModel(randomLong(), randomSideModel(), randomBoolean(), randomLong(),
                        randomLong(), randomLong(), randomLong(), randomLong(), randomLong(),
                        randomLong(), randomLong(), randomLong(), randomLong(), randomLong(),
                        randomLong())

        fun makeMatchModels(count: Int): List<MatchModel> = MutableList(count) { makeMatchModel() }

    }

}
