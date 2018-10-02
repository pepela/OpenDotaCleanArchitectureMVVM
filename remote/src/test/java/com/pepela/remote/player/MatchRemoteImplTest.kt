package com.pepela.remote.player

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.remote.player.mapper.match.MatchMapper
import com.pepela.remote.test.factory.match.MatchModelFactory.Factory.makeMatchModels
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MatchRemoteImplTest {

    companion object {
        private const val ACCOUNT_ID = 1L
    }

    private val playerService = mock<PlayerService>()
    private val matchMapper = mock<MatchMapper>()
    private val matchRemoteImpl = MatchRemoteImpl(matchMapper, playerService)

    @Test
    fun getRecentMatches_completes() {
        val matchModels = makeMatchModels(2)
        whenever(playerService.getRecentMatches(any())).thenReturn(Flowable.just(matchModels))
        val testObserver = matchRemoteImpl.getRecentMatches(ACCOUNT_ID).test()

        testObserver.assertComplete()
    }

    @Test
    fun getRecentMatches_returns_data() {
        val matchModels = makeMatchModels(2)
        whenever(playerService.getRecentMatches(any())).thenReturn(Flowable.just(matchModels))
        val testObserver = matchRemoteImpl.getRecentMatches(ACCOUNT_ID).test()

        testObserver.assertValue(matchMapper.from(matchModels))
    }

}
