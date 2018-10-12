package com.pepela.data

import com.nhaarman.mockito_kotlin.*
import com.pepela.data.source.hero.HeroDataStore
import com.pepela.data.source.hero.HeroDataStoreFactory
import com.pepela.data.test.factory.hero.HeroFactory.Factory.makeHero
import com.pepela.data.test.factory.hero.HeroFactory.Factory.makeHeroes
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDate

@RunWith(JUnit4::class)
class HeroDataRepositoryTest {

    companion object {
        private const val HERO_ID = 1L
    }

    private val heroRemoteDataStore = mock<HeroDataStore>()
    private val heroLocalDataStore = mock<HeroDataStore>()
    private val heroDataStoreFactory = mock<HeroDataStoreFactory>()

    private val heroRepository = HeroDataRepository(heroDataStoreFactory)

    @Before
    fun sutUp() {
        whenever(heroDataStoreFactory.retrieveLocalDataStore()).thenReturn(heroLocalDataStore)
        whenever(heroDataStoreFactory.retrieveRemoteDataStore()).thenReturn(heroRemoteDataStore)
        whenever(heroLocalDataStore.saveHeroes(any())).thenReturn(Completable.complete())
        whenever(heroLocalDataStore.clearHeroes()).thenReturn(Completable.complete())
    }

    @Test
    fun getHeroes_completes() {
        whenever(heroLocalDataStore.isCached()).thenReturn(Single.just(true))
        whenever(heroDataStoreFactory.retrieveDataStore(any())).thenReturn(heroLocalDataStore)
        whenever(heroLocalDataStore.getHeroes()).thenReturn(Flowable.just(makeHeroes(2)))
        whenever(heroLocalDataStore.saveHeroes(any())).thenReturn(Completable.complete())
        val testObserver = heroRepository.getHeroes().test()

        testObserver.assertComplete()
    }

    @Test
    fun getHeroes_cached_returns_data() {
        whenever(heroLocalDataStore.isCached()).thenReturn(Single.just(true))
        whenever(heroDataStoreFactory.retrieveDataStore(any())).thenReturn(heroLocalDataStore)
        val heroes = makeHeroes(2)
        whenever(heroLocalDataStore.getHeroes()).thenReturn(Flowable.just(heroes))
        val testObserver = heroRepository.getHeroes().test()

        testObserver.assertValue(heroes)
    }

    @Test
    fun getHeroes_not_cached_returns_data() {
        whenever(heroLocalDataStore.isCached()).thenReturn(Single.just(false))
        whenever(heroDataStoreFactory.retrieveDataStore(any())).thenReturn(heroRemoteDataStore)
        val heroes = makeHeroes(2)
        whenever(heroRemoteDataStore.getHeroes()).thenReturn(Flowable.just(heroes))
        val testObserver = heroRepository.getHeroes().test()

        testObserver.assertValue(heroes)
    }

    @Test
    fun clearHeroes_completes() {
        val testObserver = heroRepository.clearHeroes().test()

        testObserver.assertComplete()
    }

    @Test
    fun clearHeroes_calls_local_data_store() {
        heroRepository.clearHeroes().test()

        verify(heroLocalDataStore).clearHeroes()
    }

    @Test
    fun clearHeroes_never_calls_remote_data_store() {
        heroRepository.clearHeroes().test()

        verify(heroRemoteDataStore, never()).clearHeroes()
    }

    @Test
    fun saveHeroes_calls_local_data_store() {
        heroRepository.saveHeroes(makeHeroes(2)).test()

        verify(heroLocalDataStore).saveHeroes(any())
    }

    @Test
    fun saveHeroes_never_calls_local_remote_store() {
        heroRepository.saveHeroes(makeHeroes(2)).test()

        verify(heroRemoteDataStore, never()).saveHeroes(any())
    }

    @Test
    fun getHero_completes() {
        whenever(heroLocalDataStore.isCached()).thenReturn(Single.just(true))
        whenever(heroDataStoreFactory.retrieveDataStore(any())).thenReturn(heroLocalDataStore)
        whenever(heroLocalDataStore.getHero(any())).thenReturn(Flowable.just(makeHero()))
        val testObserver = heroRepository.getHero(HERO_ID).test()

        testObserver.assertComplete()
    }

    @Test
    fun getHero_cached_returns_data() {
        whenever(heroLocalDataStore.isCached()).thenReturn(Single.just(true))
        whenever(heroDataStoreFactory.retrieveDataStore(any())).thenReturn(heroLocalDataStore)
        val hero = makeHero()
        whenever(heroLocalDataStore.getHero(any())).thenReturn(Flowable.just(hero))
        val testObserver = heroRepository.getHero(HERO_ID).test()

        testObserver.assertValue(hero)
    }

    @Test
    fun getHero_remote_returns_data() {
        whenever(heroLocalDataStore.isCached()).thenReturn(Single.just(false))
        whenever(heroDataStoreFactory.retrieveDataStore(any())).thenReturn(heroRemoteDataStore)
        val hero = makeHero()
        whenever(heroRemoteDataStore.getHeroes()).thenReturn(Flowable.just(mutableListOf(hero)))
        val testObserver = heroRepository.getHero(hero.id).test()

        testObserver.assertValue(hero)
    }

}
