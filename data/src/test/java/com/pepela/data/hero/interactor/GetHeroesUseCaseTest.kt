package com.pepela.data.hero.interactor

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.repository.HeroRepository
import com.pepela.data.test.factory.hero.HeroFactory.Factory.makeHeroes
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetHeroesUseCaseTest {

    private val heroRepository = mock<HeroRepository>()
    private val threadExecutor = mock<ThreadExecutor>()
    private val postExecutionThread = mock<PostExecutionThread>()

    private val getHeroesUseCase =
            GetHeroesUseCase(heroRepository, threadExecutor, postExecutionThread)

    @Test
    fun getHeroes_calls_repository() {
        getHeroesUseCase.buildUseCase()

        verify(heroRepository).getHeroes()
    }

    @Test
    fun getHeroes_returns_heroes() {
        val heroes = makeHeroes(2)
        whenever(heroRepository.getHeroes()).thenReturn(Flowable.just(heroes))
        val testObserver = getHeroesUseCase.buildUseCase().test()

        testObserver.assertValue(heroes)
    }

}
