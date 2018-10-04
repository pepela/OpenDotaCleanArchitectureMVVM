package com.pepela.data

import com.nhaarman.mockito_kotlin.*
import com.pepela.data.source.player.PlayerDataStore
import com.pepela.data.source.player.PlayerDataStoreFactory
import com.pepela.data.test.factory.player.PlayerFactory.Factory.makePlayer
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PlayerDataRepositoryTest {

    private val playerRemoteDataStore = mock<PlayerDataStore>()
    private val playerLocalDataStore = mock<PlayerDataStore>()
    private val playerDataStoreFactory = mock<PlayerDataStoreFactory>()

    private val playerDataRepository = PlayerDataRepository(playerDataStoreFactory)

    companion object {
        private const val PLAYER_ID = 1L
        private const val PLAYER_NAME = "W1sh-"
    }

    @Before
    fun setUp() {
        whenever(playerDataStoreFactory.retrieveLocalDataStore()).thenReturn(playerLocalDataStore)
        whenever(playerDataStoreFactory.retrieveRemoteDataStore()).thenReturn(playerRemoteDataStore)
        whenever(playerLocalDataStore.savePlayer(any())).thenReturn(Completable.complete())
        whenever(playerLocalDataStore.clearPlayers()).thenReturn(Completable.complete())
    }

    @Test
    fun clearPlayers_completes() {
        val testObserver = playerDataRepository.clearPlayers().test()

        testObserver.assertComplete()
    }

    @Test
    fun clearPlayers_calls_local_data_store() {
        playerDataRepository.clearPlayers().test()

        verify(playerLocalDataStore).clearPlayers()
    }

    @Test
    fun clearPlayers_never_calls_remote_data_store() {
        playerDataRepository.clearPlayers().test()

        verify(playerRemoteDataStore, never()).clearPlayers()
    }

    @Test
    fun savePlayer_completes() {
        val testObserver = playerDataRepository.savePlayer(makePlayer()).test()

        testObserver.assertComplete()
    }

    @Test
    fun savePlayer_calls_local_data_store() {
        playerDataRepository.savePlayer(makePlayer())
        verify(playerLocalDataStore).savePlayer(any())
    }

    @Test
    fun savePlayer_never_calls_remote_data_store() {
        playerDataRepository.savePlayer(makePlayer())

        verify(playerRemoteDataStore, never()).savePlayer(any())
    }

    @Test
    fun getPlayer_completes() {
        whenever(playerLocalDataStore.isCached()).thenReturn(Single.just(true))
        whenever(playerDataStoreFactory.retrieveDataStore(any())).thenReturn(playerLocalDataStore)
        whenever(playerLocalDataStore.getPlayer(any())).thenReturn(Flowable.just(makePlayer()))
        whenever(playerLocalDataStore.savePlayer(any())).thenReturn(Completable.complete())
        val testObserver = playerDataRepository.getPlayer(PLAYER_ID).test()

        testObserver.assertComplete()
    }

    @Test
    fun getPlayer_cached_returns_data() {
        whenever(playerLocalDataStore.isCached()).thenReturn(Single.just(true))
        whenever(playerDataStoreFactory.retrieveDataStore(any())).thenReturn(playerLocalDataStore)
        val player = makePlayer()
        whenever(playerLocalDataStore.getPlayer(any())).thenReturn(Flowable.just(player))
        val testObserver = playerDataRepository.getPlayer(PLAYER_ID).test()

        testObserver.assertValue(player)
    }

    @Test
    fun getPlayer_not_cached_returns_data() {
        whenever(playerLocalDataStore.isCached()).thenReturn(Single.just(false))
        whenever(playerDataStoreFactory.retrieveDataStore(any())).thenReturn(playerRemoteDataStore)
        val player = makePlayer()
        whenever(playerRemoteDataStore.getPlayer(any())).thenReturn(Flowable.just(player))
        val testObserver = playerDataRepository.getPlayer(PLAYER_ID).test()

        testObserver.assertValue(player)
    }

    @Test
    fun searchPlayer_calls_remote_data_store() {
        playerDataRepository.searchPlayer(PLAYER_NAME)

        verify(playerRemoteDataStore).searchPlayer(PLAYER_NAME)
    }

    @Test
    fun searchPlayer_never_calls_local_data_store() {
        playerDataRepository.searchPlayer(PLAYER_NAME)

        verify(playerLocalDataStore, never()).searchPlayer(PLAYER_NAME)
    }

}
