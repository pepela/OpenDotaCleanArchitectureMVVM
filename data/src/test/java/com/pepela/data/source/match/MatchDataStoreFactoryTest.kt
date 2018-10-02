package com.pepela.data.source.match

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MatchDataStoreFactoryTest {

    private val remoteMatchDataStore = mock<MatchDataStore>()
    private val matchDataStoreFactory = MatchDataStoreFactory(remoteMatchDataStore)

    @Test
    fun getDataStore_returns_remote_data_store() {
        val dataStore = matchDataStoreFactory.getDataStore()

        assertEquals(dataStore, remoteMatchDataStore)
    }

    @Test
    fun getRemoteDataStore_returns_remote_data_store() {
        val dataStore = matchDataStoreFactory.getRemoteDataStore()

        assertEquals(dataStore, remoteMatchDataStore)
    }

}
