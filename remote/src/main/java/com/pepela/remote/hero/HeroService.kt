package com.pepela.remote.hero

import com.pepela.remote.hero.model.HeroModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface HeroService {

    @GET("/api/heroes")
    fun getHeroes(): Flowable<List<HeroModel>>

}
