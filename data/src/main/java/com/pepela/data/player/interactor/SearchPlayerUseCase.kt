package com.pepela.data.player.interactor

import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.interpretator.FlowableUseCase
import com.pepela.data.player.model.Profile
import com.pepela.data.repository.PlayerRepository
import io.reactivex.Flowable

class SearchPlayerUseCase(private val playerRepository: PlayerRepository,
                          threadExecutor: ThreadExecutor,
                          postExecutionThread: PostExecutionThread)
    : FlowableUseCase<List<Profile>, String>(threadExecutor, postExecutionThread) {

    public override fun buildUseCase(params: String?): Flowable<List<Profile>> {
        return playerRepository.searchPlayer(params ?: "")
    }

}
