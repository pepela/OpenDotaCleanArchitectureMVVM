package com.pepela.remote.player

import com.pepela.remote.RetrofitFactory

object PlayerServiceFactory {

    fun makePlayerService(isDebug: Boolean): PlayerService =
            RetrofitFactory.makeRetrofit(isDebug).create(PlayerService::class.java)

}
