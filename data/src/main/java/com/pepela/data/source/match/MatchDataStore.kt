package com.pepela.data.source.match

import com.pepela.data.match.model.Match
import io.reactivex.Flowable

interface MatchDataStore {

    fun getRecentMatches(accountId: Long): Flowable<List<Match>>

}
