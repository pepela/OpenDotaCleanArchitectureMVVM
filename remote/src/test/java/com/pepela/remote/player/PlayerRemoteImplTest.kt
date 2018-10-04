package com.pepela.remote.player

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.remote.player.mapper.player.PlayerMapper
import com.pepela.remote.player.mapper.player.ProfileMapper
import com.pepela.remote.player.mapper.player.RankMapper
import com.pepela.remote.player.mapper.player.SearchProfileMapper
import com.pepela.remote.test.factory.player.PlayerModelFactory.Factory.makePlayerModel
import com.pepela.remote.test.factory.player.SearchProfileFactory.Factory.makeSearchProfileModels
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PlayerRemoteImplTest {

    companion object {
        private const val PLAYER_ID = 1L
        private const val PLAYER_NAME = "W1sh-"
    }

    private val rankMapper = RankMapper()
    private val profileMapper = ProfileMapper()
    private val searchProfileMapper = SearchProfileMapper()
    private val playerMapper = PlayerMapper(rankMapper, profileMapper)

    private val playerService = mock<PlayerService>()

    private val playerRemoteImpl = PlayerRemoteImpl(playerService, playerMapper, searchProfileMapper)

    @Test
    fun getPlayer_completes() {
        whenever(playerService.getPlayer(any())).thenReturn(Flowable.just(makePlayerModel()))
        val testObserver = playerRemoteImpl.getPlayer(PLAYER_ID).test()

        testObserver.assertComplete()
    }

    @Test
    fun getPlayer_returns_data() {
        val playerModel = makePlayerModel()
        whenever(playerService.getPlayer(any())).thenReturn(Flowable.just(playerModel))
        val testObserver = playerRemoteImpl.getPlayer(PLAYER_ID).test()

        testObserver.assertValue(playerMapper.from(playerModel))
    }

    @Test
    fun searchPlayer_completes() {
        val searchProfileModels = makeSearchProfileModels(2)
        whenever(playerService.searchPlayers(PLAYER_NAME)).thenReturn(Flowable.just(searchProfileModels))
        val testObserver = playerRemoteImpl.searchPlayer(PLAYER_NAME).test()

        testObserver.assertComplete()
    }

    @Test
    fun searchPlayer_returns_data() {
        val searchProfileModels = makeSearchProfileModels(2)
        whenever(playerService.searchPlayers(PLAYER_NAME)).thenReturn(Flowable.just(searchProfileModels))
        val testObserver = playerRemoteImpl.searchPlayer(PLAYER_NAME).test()

        testObserver.assertValue(searchProfileMapper.from(searchProfileModels))
    }

}
