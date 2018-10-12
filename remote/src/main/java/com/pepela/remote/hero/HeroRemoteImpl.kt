package com.pepela.remote.hero

import com.pepela.data.hero.model.Hero
import com.pepela.data.source.hero.HeroDataStore
import com.pepela.remote.hero.mapper.HeroMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class HeroRemoteImpl(private val heroService: HeroService,
                     private val heroMapper: HeroMapper) : HeroDataStore {

    override fun getHero(id: Long): Flowable<Hero> {
        throw IllegalStateException("Remote data store can't save player")
    }

    override fun getHeroes(): Flowable<List<Hero>> =
            heroService.getHeroes().map { heroMapper.from(it) }

    override fun saveHeroes(heroes: List<Hero>): Completable {
        throw IllegalStateException("Remote data store can't save heros")
    }

    override fun clearHeroes(): Completable {
        throw IllegalStateException("Remote data store can't clear heroes")
    }

    override fun isCached(): Single<Boolean> {
        throw IllegalStateException("Remote data store can't be cached")
    }

}
