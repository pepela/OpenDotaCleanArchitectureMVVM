package com.pepela.data.test.factory.player

import com.pepela.data.player.model.SearchProfile
import com.pepela.data.test.factory.DataFactory.Factory.randomLong
import com.pepela.data.test.factory.DataFactory.Factory.randomUuid
import java.util.*

class SearchProfileFactory {

    companion object Factory {

        fun makeSearchProfile() =
                SearchProfile(randomUuid(), randomLong(), randomUuid(), Date(randomLong()))

        fun makeSearchProfiles(count: Int) = MutableList(count) { makeSearchProfile() }
    }
}
