package com.pepela.cache.hero

import com.pepela.data.hero.model.Hero
import com.pepela.data.source.hero.HeroDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class HeroCacheImpl : HeroDataStore {

    override fun getHero(id: Long): Flowable<Hero> {
        TODO("not implemented")
    }

    override fun getHeroes(): Flowable<List<Hero>> {
        TODO("not implemented")
    }

    override fun saveHeroes(heroes: List<Hero>): Completable {
        TODO("not implemented")
    }

    override fun clearHeroes(): Completable {
        TODO("not implemented")
    }

    override fun isCached(): Single<Boolean> = Single.just(false)

}
