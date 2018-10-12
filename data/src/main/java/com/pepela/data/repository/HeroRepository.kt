package com.pepela.data.repository

import com.pepela.data.hero.model.Hero
import io.reactivex.Completable
import io.reactivex.Flowable

interface HeroRepository {

    fun getHero(id: Long): Flowable<Hero>

    fun getHeroes(): Flowable<List<Hero>>

    fun saveHeroes(heroes: List<Hero>): Completable

    fun clearHeroes(): Completable

}
