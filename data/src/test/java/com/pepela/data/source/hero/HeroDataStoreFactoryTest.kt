package com.pepela.data.source.hero

import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class HeroDataStoreFactoryTest {

    private val remote: HeroDataStore = mock<HeroDataStore>()
    private val local: HeroDataStore = mock<HeroDataStore>()
    private val heroDataStoreFactory = HeroDataStoreFactory(remote, local)

    @Test
    fun request_data_store_when_is_cached_returns_local_data_store() {
        val dataStore = heroDataStoreFactory.retrieveDataStore(true)

        assertEquals(dataStore, local)
    }

    @Test
    fun request_data_store_when_not_cached_returns_remote_data_store() {
        val dataStore = heroDataStoreFactory.retrieveDataStore(false)

        assertEquals(dataStore, remote)
    }

    @Test
    fun request_local_data_store_returns_local_data_store() {
        val dataStore = heroDataStoreFactory.retrieveLocalDataStore()

        assertEquals(dataStore, local)
    }

    @Test
    fun request_remote_data_store_returns_remote_data_store() {
        val dataStore = heroDataStoreFactory.retrieveRemoteDataStore()

        assertEquals(dataStore, remote)

    }
}
