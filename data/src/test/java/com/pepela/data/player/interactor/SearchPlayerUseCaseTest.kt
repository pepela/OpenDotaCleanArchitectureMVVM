package com.pepela.data.player.interactor

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.repository.PlayerRepository
import com.pepela.data.test.factory.player.ProfileFactory.Factory.makeProfiles
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchPlayerUseCaseTest {

    companion object {
        private const val PLAYER_NAME = "W1sh-"
    }

    private val playerRepository = mock<PlayerRepository>()
    private val threadExecutor = mock<ThreadExecutor>()
    private val postExecutionThread = mock<PostExecutionThread>()
    private val searchPlayerUseCase =
            SearchPlayerUseCase(playerRepository, threadExecutor, postExecutionThread)

    @Test
    fun searchPlayerUseCase_calls_repository() {
        searchPlayerUseCase.buildUseCase(PLAYER_NAME)

        verify(playerRepository).searchPlayer(PLAYER_NAME)
    }

    @Test
    fun searchPlayerUseCase_returns_data() {
        val profiles = makeProfiles(2)
        whenever(playerRepository.searchPlayer(any())).thenReturn(Flowable.just(profiles))
        val testObserver = searchPlayerUseCase.buildUseCase(PLAYER_NAME).test()

        testObserver.assertValue(profiles)
    }

}
