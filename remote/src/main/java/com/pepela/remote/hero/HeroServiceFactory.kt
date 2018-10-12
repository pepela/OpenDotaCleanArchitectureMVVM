package com.pepela.remote.hero

import com.pepela.remote.RetrofitFactory

object HeroServiceFactory {

    fun makeHeroService(isDebug: Boolean): HeroService =
            RetrofitFactory.makeRetrofit(isDebug).create(HeroService::class.java)

}
