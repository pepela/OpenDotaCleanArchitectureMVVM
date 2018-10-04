package com.pepela.remote.test.factory.player

import com.pepela.remote.player.model.player.SearchProfileModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong
import com.pepela.remote.test.factory.DataFactory.Factory.randomUuid
import java.util.*

class SearchProfileFactory {

    companion object Factory {

        fun makeSearchProfileModel() =
                SearchProfileModel(randomUuid(), randomLong(), randomUuid(), Date(randomLong()))

        fun makeSearchProfileModels(count: Int) = MutableList(count) { makeSearchProfileModel() }

    }
}
