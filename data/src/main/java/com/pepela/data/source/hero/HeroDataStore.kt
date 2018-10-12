package com.pepela.data.source.hero

import com.pepela.data.hero.model.Hero
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface HeroDataStore {

    fun getHero(id: Long): Flowable<Hero>

    fun getHeroes(): Flowable<List<Hero>>

    fun saveHeroes(heroes: List<Hero>): Completable

    fun clearHeroes(): Completable

    fun isCached(): Single<Boolean>

}
