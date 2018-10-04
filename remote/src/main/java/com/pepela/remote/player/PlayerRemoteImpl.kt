package com.pepela.remote.player

import com.pepela.data.player.model.Player
import com.pepela.data.player.model.Profile
import com.pepela.data.source.player.PlayerDataStore
import com.pepela.remote.player.mapper.player.PlayerMapper
import com.pepela.remote.player.mapper.player.ProfileMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class PlayerRemoteImpl(private val playerService: PlayerService,
                       private val playerMapper: PlayerMapper,
                       private val profileMapper: ProfileMapper)
    : PlayerDataStore {

    override fun getPlayer(id: Long): Flowable<Player> =
            playerService.getPlayer(id).map { playerMapper.from(it) }

    override fun searchPlayer(name: String): Flowable<List<Profile>> =
            playerService.searchPlayers(name).map { profileMapper.from(it) }

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
