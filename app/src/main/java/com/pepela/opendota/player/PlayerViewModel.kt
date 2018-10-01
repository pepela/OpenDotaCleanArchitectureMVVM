package com.pepela.opendota.player

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.pepela.data.player.interceptor.GetPlayerUseCase
import io.reactivex.disposables.Disposable
import timber.log.Timber

class PlayerViewModel(private val getPlayerUseCase: GetPlayerUseCase) : ViewModel() {

    private val playerLiveData: MutableLiveData<PlayerState> = MutableLiveData()
    private var disposable: Disposable? = null

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }

    fun getPlayer(): LiveData<PlayerState> = playerLiveData

    fun fetchPlayer(accountId: Long) {
        playerLiveData.postValue(PlayerState.Loading)
        disposable = getPlayerUseCase.execute(accountId)
                .subscribe({
                    Timber.d(it.toString())
                    playerLiveData.postValue(PlayerState.Success(it))
                }, {
                    Timber.e(it)
                    playerLiveData.postValue(PlayerState.Error(it.message))
                })
    }

}
