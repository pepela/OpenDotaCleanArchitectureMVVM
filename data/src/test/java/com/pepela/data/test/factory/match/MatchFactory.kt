package com.pepela.data.test.factory.match

import com.pepela.data.match.model.Match
import com.pepela.data.test.factory.DataFactory.Factory.randomBoolean
import com.pepela.data.test.factory.DataFactory.Factory.randomLong
import java.util.*

class MatchFactory {

    companion object Factory {

        fun makeMatch(): Match =
                Match(randomLong(), randomBoolean(), randomLong(), randomLong(), Date(randomLong()),
                        randomLong(), randomLong(), randomLong(), randomLong(), randomLong(),
                        randomLong(), randomLong(), randomLong(), randomLong())

        fun makeMatches(count: Int): List<Match> = MutableList(count) { makeMatch() }

    }

}
