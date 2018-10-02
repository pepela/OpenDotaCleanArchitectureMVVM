package com.pepela.data.repository

import com.pepela.data.match.model.Match
import io.reactivex.Flowable

interface MatchRepository {

    fun getRecentMatches(accountId: Long): Flowable<List<Match>>

}
