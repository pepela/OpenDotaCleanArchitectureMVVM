package com.pepela.remote.player

import com.pepela.data.player.model.Player
import com.pepela.data.source.PlayerDataStore
import com.pepela.remote.player.mapper.PlayerMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PlayerRemoteImpl(val playerService: PlayerService, val playerMapper: PlayerMapper)
    : PlayerDataStore {

    override fun getPlayer(id: Long): Flowable<Player> =
            playerService.getPlayer(id).map { playerMapper.from(it) }

    override fun savePlayer(player: Player): Completable {
        throw IllegalStateException("Remote data store can't save player")
    }

    override fun clearPlayers(): Completable {
        throw IllegalStateException("Remote data store can't clear player")
    }

    override fun isCached(): Single<Boolean> {
        throw IllegalStateException("Remote data store can't be cached")
    }

}
