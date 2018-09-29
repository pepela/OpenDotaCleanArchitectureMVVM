package com.pepela.remote.player

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.remote.player.mapper.PlayerMapper
import com.pepela.remote.player.mapper.ProfileMapper
import com.pepela.remote.player.mapper.RankMapper
import com.pepela.remote.player.test.factory.PlayerModelFactory.Factory.makePlayerModel
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PlayerRemoteImplTest {

    companion object {
        private const val PLAYER_ID = 1L
    }

    private val rankMapper = RankMapper()
    private val profileMapper = ProfileMapper()
    private val playerMapper = PlayerMapper(rankMapper, profileMapper)

    private val playerService = mock<PlayerService>()

    private val playerRemoteImpl = PlayerRemoteImpl(playerService, playerMapper)

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

}
