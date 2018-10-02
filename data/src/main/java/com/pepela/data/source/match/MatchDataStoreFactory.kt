package com.pepela.data.source.match

open class MatchDataStoreFactory(private val remote: MatchDataStore) {

    open fun getDataStore(): MatchDataStore = getRemoteDataStore()

    open fun getRemoteDataStore(): MatchDataStore = remote

}
