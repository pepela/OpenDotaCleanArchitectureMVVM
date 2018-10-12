package com.pepela.data.source.hero

open class HeroDataStoreFactory(private val remote: HeroDataStore,
                                private val local: HeroDataStore) {

    open fun retrieveDataStore(isCached: Boolean) =
            if (isCached) retrieveLocalDataStore() else retrieveRemoteDataStore()

    open fun retrieveLocalDataStore() = local

    open fun retrieveRemoteDataStore() = remote

}
