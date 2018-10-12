package com.pepela.data.source.player

import com.pepela.data.player.model.Player
import com.pepela.data.player.model.Profile
import com.pepela.data.player.model.SearchProfile
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PlayerDataStore {

    fun getPlayer(id: Long): Flowable<Player>

    fun savePlayer(player: Player): Completable

    fun clearPlayers(): Completable

    fun isCached(): Single<Boolean>

    fun searchPlayer(name: String): Flowable<List<SearchProfile>>

}
