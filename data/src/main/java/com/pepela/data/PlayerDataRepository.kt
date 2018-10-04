package com.pepela.data

import com.pepela.data.player.model.Player
import com.pepela.data.player.model.Profile
import com.pepela.data.repository.PlayerRepository
import com.pepela.data.source.player.PlayerDataStoreFactory
import io.reactivex.Flowable

class PlayerDataRepository(private val factory: PlayerDataStoreFactory)
    : PlayerRepository {

    override fun getPlayer(id: Long): Flowable<Player> =
            factory.retrieveLocalDataStore().isCached()
                    .flatMapPublisher {
                        factory.retrieveDataStore(it).getPlayer(id)
                    }
                    .flatMap {
                        savePlayer(it).toSingle { it }.toFlowable()
                    }

    override fun savePlayer(player: Player) = factory.retrieveLocalDataStore().savePlayer(player)

    override fun clearPlayers() = factory.retrieveLocalDataStore().clearPlayers()

    override fun searchPlayer(name: String): Flowable<List<Profile>> =
            factory.retrieveRemoteDataStore().searchPlayer(name)
}
