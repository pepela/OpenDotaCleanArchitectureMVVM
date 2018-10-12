package com.pepela.data

import com.pepela.data.hero.model.Hero
import com.pepela.data.repository.HeroRepository
import com.pepela.data.source.hero.HeroDataStoreFactory
import io.reactivex.Completable
import io.reactivex.Flowable

class HeroDataRepository(private val factory: HeroDataStoreFactory) : HeroRepository {

    override fun getHero(id: Long): Flowable<Hero> =
            factory.retrieveLocalDataStore().isCached()
                    .flatMapPublisher {isCached ->
                        if (isCached) {
                            factory.retrieveLocalDataStore().getHero(id)
                        } else {
                            getHeroes()
                                    .flatMap { Flowable.fromIterable(it) }
                                    .filter { it.id == id }
                        }
                    }

    override fun getHeroes(): Flowable<List<Hero>> =
            factory.retrieveLocalDataStore().isCached()
                    .flatMapPublisher { factory.retrieveDataStore(it).getHeroes() }
                    .flatMap { saveHeroes(it).toSingle { it }.toFlowable() }

    override fun saveHeroes(heroes: List<Hero>): Completable =
            factory.retrieveLocalDataStore().saveHeroes(heroes)

    override fun clearHeroes(): Completable =
            factory.retrieveLocalDataStore().clearHeroes()

}
