package com.pepela.opendota.player

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.data.player.interactor.GetPlayerUseCase
import com.pepela.opendota.test.factory.DataFactory
import com.pepela.opendota.test.factory.PlayerFactory
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test


class PlayerViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    val mockGetPlayerUseCase = mock<GetPlayerUseCase>()

    private val playerViewModel = PlayerViewModel(mockGetPlayerUseCase)

    @Test
    fun getPlayer_returns_loading() {
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value is PlayerState.Loading)
    }

    @Test
    fun getPlayer_has_no_data_when_loading() {
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value?.data == null)
    }

    @Test
    fun getPlayer_has_no_message_when_loading() {
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value?.errorMessage == null)
    }

    @Test
    fun getPlayer_returns_success() {
        whenever(mockGetPlayerUseCase.execute(any())).thenReturn(Flowable.just(PlayerFactory.makePlayer()))
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value is PlayerState.Success)
    }

    @Test
    fun getPlayer_returns_data_when_success() {
        val player = PlayerFactory.makePlayer()
        whenever(mockGetPlayerUseCase.execute(any())).thenReturn(Flowable.just(player))
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value?.data == player)
    }

    @Test
    fun getPlayer_no_message_when_success() {
        whenever(mockGetPlayerUseCase.execute(any())).thenReturn(Flowable.just(PlayerFactory.makePlayer()))
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value?.errorMessage == null)
    }

    @Test
    fun getPlayer_returns_error() {
        whenever(mockGetPlayerUseCase.execute(any())).thenReturn(Flowable.error(RuntimeException()))
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value is PlayerState.Error)
    }

    @Test
    fun getPlayer_has_no_data_when_error() {
        whenever(mockGetPlayerUseCase.execute(any())).thenReturn(Flowable.error(RuntimeException()))
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value?.data == null)
    }

    @Test
    fun getPlayer_has_message_when_error() {
        val errorMessage = DataFactory.randomUuid()
        whenever(mockGetPlayerUseCase.execute(any()))
                .thenReturn(Flowable.error(RuntimeException(errorMessage)))
        playerViewModel.getPlayer()

        assert(playerViewModel.getPlayer().value?.errorMessage == errorMessage)
    }

}