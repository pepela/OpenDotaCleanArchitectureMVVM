package com.pepela.opendota.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pepela.data.match.interactor.GetRecentMatchesUseCase
import com.pepela.data.player.interactor.GetPlayerUseCase
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class PlayerViewModel(private val getPlayerUseCase: GetPlayerUseCase,
                      private val getRecentMatchesUseCase: GetRecentMatchesUseCase)
    : ViewModel() {

    private val playerLiveData: MutableLiveData<PlayerState> = MutableLiveData()
    private val recentMatchesLiveData: MutableLiveData<RecentMatchesState> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getPlayer(): LiveData<PlayerState> = playerLiveData

    fun getRecentMatches(): LiveData<RecentMatchesState> = recentMatchesLiveData

    fun fetchPlayer(accountId: Long) {
        playerLiveData.postValue(PlayerState.Loading)
        compositeDisposable.add(getPlayerUseCase.execute(accountId)
                .subscribe({
                    Timber.d(it.toString())
                    playerLiveData.postValue(PlayerState.Success(it))
                }, {
                    Timber.e(it)
                    playerLiveData.postValue(PlayerState.Error(it.message))
                }))
    }

    fun fetchRecentMatches(accountId: Long) {
        recentMatchesLiveData.postValue(RecentMatchesState.Loading)
        compositeDisposable.add(getRecentMatchesUseCase.execute(accountId)
                .subscribe({
                    Timber.d(it.toString())
                    recentMatchesLiveData.postValue(RecentMatchesState.Success(it))
                }, {
                    Timber.e(it)
                    recentMatchesLiveData.postValue(RecentMatchesState.Error(it.message))
                }))
    }

}
