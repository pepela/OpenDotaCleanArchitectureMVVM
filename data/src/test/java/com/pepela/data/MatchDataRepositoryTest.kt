package com.pepela.data

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.data.source.match.MatchDataStore
import com.pepela.data.source.match.MatchDataStoreFactory
import com.pepela.data.test.factory.match.MatchFactory.Factory.makeMatches
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MatchDataRepositoryTest {

    companion object {
        private const val ACCOUNT_ID = 1L
    }

    private val remoteMatchDataStore = mock<MatchDataStore>()
    private val matchDataStoreFactory = mock<MatchDataStoreFactory>()
    private val matchRepository = MatchDataRepository(matchDataStoreFactory)

    @Before
    fun setUp() {
        whenever(matchDataStoreFactory.getDataStore()).thenReturn(remoteMatchDataStore)
        whenever(matchDataStoreFactory.getRemoteDataStore()).thenReturn(remoteMatchDataStore)
    }

    @Test
    fun getMatches_completes() {
        val matches = makeMatches(2)
        whenever(remoteMatchDataStore.getRecentMatches(any())).thenReturn(Flowable.just(matches))
        val testObserver = matchRepository.getRecentMatches(ACCOUNT_ID).test()

        testObserver.assertComplete()
    }

    @Test
    fun getMatches_returns_matches() {
        val matches = makeMatches(2)
        whenever(remoteMatchDataStore.getRecentMatches(any())).thenReturn(Flowable.just(matches))
        val testObserver = matchRepository.getRecentMatches(ACCOUNT_ID).test()

        testObserver.assertValue(matches)
    }

}
