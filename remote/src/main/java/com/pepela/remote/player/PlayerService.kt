package com.pepela.remote.player

import com.pepela.remote.player.model.match.MatchModel
import com.pepela.remote.player.model.player.PlayerModel
import com.pepela.remote.player.model.player.SearchProfileModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerService {

    @GET("/api/players/{account_id}")
    fun getPlayer(@Path("account_id") id: Long): Flowable<PlayerModel>

    @GET("/api/players/{account_id}/recentMatches")
    fun getRecentMatches(@Path("account_id") id: Long): Flowable<List<MatchModel>>

    @GET("/api/search?")
    fun searchPlayers(@Query("q") name: String): Flowable<List<SearchProfileModel>>

}
