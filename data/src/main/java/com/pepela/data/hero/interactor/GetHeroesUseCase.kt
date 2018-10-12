package com.pepela.data.hero.interactor

import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.hero.model.Hero
import com.pepela.data.interpretator.FlowableUseCase
import com.pepela.data.repository.HeroRepository
import io.reactivex.Flowable

open class GetHeroesUseCase(private val heroRepository: HeroRepository,
                            executor: ThreadExecutor,
                            postExecutionThread: PostExecutionThread)
    : FlowableUseCase<List<Hero>, Void?>(executor, postExecutionThread) {

    public override fun buildUseCase(params: Void?): Flowable<List<Hero>> {
        return heroRepository.getHeroes()
    }

}
