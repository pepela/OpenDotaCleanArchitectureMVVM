package com.pepela.data.source

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PlayerDataStoreFactoryTest {

    private val remoteDataStore = mock<PlayerDataStore>()
    private val localDataStore = mock<PlayerDataStore>()
    private val playerDataStoreFactory = PlayerDataStoreFactory(remoteDataStore, localDataStore)

    @Test
    fun request_data_store_when_is_cached_returns_local_data_store() {
        val dataStore = playerDataStoreFactory.retrieveDataStore(true)
        assertEquals(dataStore, localDataStore)
    }

    @Test
    fun request_data_store_when_not_cached_returns_remote_data_store() {
        val dataStore = playerDataStoreFactory.retrieveDataStore(false)
        assertEquals(dataStore, remoteDataStore)
    }

    @Test
    fun request_local_data_store_returns_local_data_store() {
        val dataStore = playerDataStoreFactory.retrieveLocalDataStore()
        assertEquals(dataStore, localDataStore)
    }

    @Test
    fun request_remote_data_store_returns_remote_data_store() {
        val dataStore = playerDataStoreFactory.retrieveRemoteDataStore()
        assertEquals(dataStore, remoteDataStore)
    }

}
