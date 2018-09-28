package com.pepela.data.source

import com.pepela.data.player.model.Player
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PlayerDataStore {

    fun getPlayer(id: Long): Flowable<Player>

    fun savePlayer(player: Player): Completable

    fun clearPlayers(): Completable

    fun isCached(): Single<Boolean>

}
