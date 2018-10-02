package com.pepela.data

import com.pepela.data.match.model.Match
import com.pepela.data.repository.MatchRepository
import com.pepela.data.source.match.MatchDataStoreFactory
import io.reactivex.Flowable

open class MatchDataRepository(private val factory: MatchDataStoreFactory) : MatchRepository {

    override fun getRecentMatches(accountId: Long): Flowable<List<Match>> =
            factory.getDataStore().getRecentMatches(accountId)

}
