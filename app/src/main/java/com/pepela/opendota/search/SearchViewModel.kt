package com.pepela.opendota.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.pepela.data.player.interactor.SearchProfileUseCase
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class SearchViewModel(private val searchProfileUseCase: SearchProfileUseCase) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    private val profileLiveData: MutableLiveData<SearchState> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getProfileLiveData(): MutableLiveData<SearchState> = profileLiveData

    fun fetchProfiles(name: String) {
        profileLiveData.postValue(SearchState.Loading)
        compositeDisposable.add(searchProfileUseCase.execute(name)
                .subscribe({
                    Timber.d(it.toString())
                    profileLiveData.postValue(SearchState.Success(it))
                }, {
                    Timber.e(it)
                    profileLiveData.postValue(SearchState.Error(it.message))
                }))
    }


}
