package com.pepela.remote.player

import com.pepela.data.match.model.Match
import com.pepela.data.source.match.MatchDataStore
import com.pepela.remote.player.mapper.match.MatchMapper
import io.reactivex.Flowable

class MatchRemoteImpl(private val matchMapper: MatchMapper,
                      private val playerService: PlayerService) : MatchDataStore {

    override fun getRecentMatches(accountId: Long): Flowable<List<Match>> =
            playerService.getRecentMatches(accountId)
                    .map { matchMapper.from(it) }

}
