package com.pepela.remote.player

import com.pepela.remote.player.model.match.MatchModel
import com.pepela.remote.player.model.player.PlayerModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerService {

    @GET("/api/players/{account_id}")
    fun getPlayer(@Path("account_id") id: Long): Flowable<PlayerModel>


    @GET("/api/players/{account_id}/recentMatches")
    fun getRecentMatches(@Path("account_id") id: Long): Flowable<List<MatchModel>>

}
