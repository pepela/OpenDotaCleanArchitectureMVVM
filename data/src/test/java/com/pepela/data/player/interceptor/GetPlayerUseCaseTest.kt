package com.pepela.data.player.interceptor

import com.nhaarman.mockito_kotlin.*
import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.repository.PlayerRepository
import com.pepela.data.test.factory.PlayerFactory.Factory.makePlayer
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetPlayerUseCaseTest {

    companion object {
        const val PLAYER_ID = 1L
    }

    val playerRepository = mock<PlayerRepository>()
    val threadExecutor = mock<ThreadExecutor>()
    val postExecutionThread = mock<PostExecutionThread>()

    val getPlayerUseCase = GetPlayerUseCase(playerRepository, threadExecutor, postExecutionThread)


    @Test
    fun getPlayer_calls_repository() {
        getPlayerUseCase.buildUseCase(PLAYER_ID)
        verify(playerRepository).getPlayer(PLAYER_ID)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getPlayer_null_parameter_throws_exception() {
        getPlayerUseCase.buildUseCase(null)
        verify(playerRepository, never()).getPlayer(any())
    }

    @Test
    fun getPLayer_returns_data() {
        val player = makePlayer()
        whenever(playerRepository.getPlayer(any())).thenReturn(Flowable.just(player))
        val testObserver = getPlayerUseCase.buildUseCase(PLAYER_ID).test()
        testObserver.assertValue(player)
    }

}
