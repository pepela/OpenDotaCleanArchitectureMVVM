package com.pepela.data.source

open class PlayerDataStoreFactory(private val remote: PlayerDataStore,
                             private val local: PlayerDataStore) {

    open fun retrieveDataStore(isCached: Boolean) =
            if (isCached) retrieveLocalDataStore() else retrieveRemoteDataStore()

    open fun retrieveLocalDataStore() = local

    open fun retrieveRemoteDataStore() = remote

}
