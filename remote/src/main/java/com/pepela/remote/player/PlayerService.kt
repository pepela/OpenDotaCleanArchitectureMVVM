package com.pepela.remote.player

import com.pepela.remote.player.model.PlayerModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerService {

    @GET("/players/{account_id}")
    fun getPlayer(@Path("account_id") id: Long): Flowable<PlayerModel>

}
