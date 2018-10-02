package com.pepela.remote.test.factory.match

import com.pepela.remote.player.model.match.MatchModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomBoolean
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong
import java.util.*

class MatchModelFactory {

    companion object Factory {

        fun makeMatchModel(): MatchModel =
                MatchModel(randomLong(), randomBoolean(), randomLong(), randomLong(),
                        Date(randomLong()), randomLong(), randomLong(), randomLong(), randomLong(),
                        randomLong(), randomLong(), randomLong(), randomLong(), randomLong())

        fun makeMatchModels(count: Int): List<MatchModel> = MutableList(count) { makeMatchModel() }

    }

}
