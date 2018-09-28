package com.pepela.data.repository

import com.pepela.data.player.model.Player
import io.reactivex.Completable
import io.reactivex.Flowable

interface PlayerRepository {

    fun getPlayer(id: Long): Flowable<Player>

    fun savePlayer(player: Player): Completable

    fun clearPlayers(): Completable

}
