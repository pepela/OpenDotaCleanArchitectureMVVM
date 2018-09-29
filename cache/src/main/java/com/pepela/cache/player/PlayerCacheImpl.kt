package com.pepela.cache.player

import com.pepela.data.player.model.Player
import com.pepela.data.source.PlayerDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PlayerCacheImpl : PlayerDataStore {
    
    override fun getPlayer(id: Long): Flowable<Player> {
        TODO("not implemented")
    }

    override fun savePlayer(player: Player): Completable {
        TODO("not implemented")
    }

    override fun clearPlayers(): Completable {
        TODO("not implemented")
    }

    override fun isCached(): Single<Boolean> = Single.just(false)

}
