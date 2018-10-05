package com.pepela.opendota.search

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.pepela.data.player.model.SearchProfile
import com.pepela.opendota.R
import com.pepela.opendota.extension.invisible
import com.pepela.opendota.extension.visible
import com.pepela.opendota.platform.BaseActivity
import com.pepela.opendota.player.PlayerActivity
import com.pepela.opendota.player.PlayerActivity.Companion.EXTRA_ACCOUNT_ID
import com.pepela.opendota.widget.empty.EmptyListener
import com.pepela.opendota.widget.error.ErrorListener
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getOrCreateScope
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SearchActivity : BaseActivity() {

    private val searchProfileAdapter: SearchProfileAdapter by inject()

    private val searchViewModel: SearchViewModel by viewModel()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindScope(getOrCreateScope("Search"))

        setContentView(R.layout.activity_search)

        setUpListeners()
        setUpProfilesRecyclerView()

        observerSearchViewModel()

        RxTextView.textChanges(name_et)
                .delay(500, TimeUnit.MILLISECONDS)
                .filter { it.length > 1 }
                .subscribe { searchViewModel.fetchProfiles(it.toString()) }
    }


    private fun observerSearchViewModel() {
        searchViewModel.getProfileLiveData().observe(this,
                Observer<SearchState> {
                    if (it != null) {
                        this.handleSearchState(it)
                    }
                })
    }

    private fun handleSearchState(searchState: SearchState) {
        when (searchState) {
            is SearchState.Loading -> setUpScreenForLoading()
            is SearchState.Success -> setUpScreenForSuccess(searchState.data)
            is SearchState.Error -> setUpScreenForError(searchState.message)
        }
    }

    private fun setUpScreenForLoading() {
        progress.visible()
        error_view.invisible()
        empty_view.invisible()
    }

    private fun setUpScreenForSuccess(profiles: List<SearchProfile>?) {
        progress.invisible()
        error_view.invisible()

        if (profiles != null && profiles.isNotEmpty()) {
            profiles_rv.visible()
            empty_view.invisible()
            searchProfileAdapter.items = profiles
            searchProfileAdapter.notifyDataSetChanged()
        } else {
            profiles_rv.invisible()
            empty_view.visible()
        }
    }

    private fun setUpScreenForError(errorMessage: String?) {
        error_view.visible()
        empty_view.invisible()
        profiles_rv.invisible()
        progress.invisible()

        displayErrorMessage(errorMessage)
    }

    private fun setUpProfilesRecyclerView() {
        profiles_rv.adapter = searchProfileAdapter
        profiles_rv.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpListeners() {
        searchProfileAdapter.listener = profileClickListener
        empty_view.listener = emptyListener
        error_view.listener = errorListener
    }

    private val errorListener = object : ErrorListener {
        override fun onRetryClicked() {
            searchViewModel.fetchProfiles(name_et.text.toString())
        }
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            searchViewModel.fetchProfiles(name_et.text.toString())
        }
    }

    private val profileClickListener = object : ProfileClickListener {
        override fun onProfileClicked(searchProfile: SearchProfile) {
            startActivity<PlayerActivity>(EXTRA_ACCOUNT_ID to searchProfile.accountId)
        }

    }
}

