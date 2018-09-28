package com.pepela.data.player.interceptor

import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.interpretator.FlowableUseCase
import com.pepela.data.player.model.Player
import com.pepela.data.repository.PlayerRepository
import io.reactivex.Flowable

open class GetPlayerUseCase(
        val repository: PlayerRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread)
    : FlowableUseCase<Player, Long?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCase(params: Long?): Flowable<Player> {
        if (params == null) {
            throw IllegalArgumentException("Player account id can't be null")
        }
        return repository.getPlayer(params)
    }

}
