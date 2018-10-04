package com.pepela.opendota.player

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pepela.data.match.model.Match
import com.pepela.data.player.model.Player
import com.pepela.opendota.platform.BaseActivity
import com.pepela.opendota.R
import com.pepela.opendota.extension.invisible
import com.pepela.opendota.extension.visible
import com.pepela.opendota.widget.empty.EmptyListener
import com.pepela.opendota.widget.error.ErrorListener
import kotlinx.android.synthetic.main.activity_player.*
import org.koin.android.ext.android.inject
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getCurrentScope
import org.koin.android.viewmodel.ext.android.viewModel

class PlayerActivity : BaseActivity() {

    companion object {
        const val EXTRA_ACCOUNT_ID = "extra_account_id"
    }

    val matchAdapter: MatchAdapter by inject()

    val playerViewModel: PlayerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        bindScope(getCurrentScope())

        setUpViewListeners()
        setUpRecentMatchAdapter()

        observePlayerFromPlayerViewModel()
        observeMatchesFromPlayerModel()
    }

    private fun setUpRecentMatchAdapter() {
        recent_matches_rv.layoutManager = LinearLayoutManager(this)
        recent_matches_rv.adapter = matchAdapter
    }

    private fun observeMatchesFromPlayerModel() {
        playerViewModel.getRecentMatches()
                .observe(this, Observer<RecentMatchesState> {
                    if (it != null) {
                        this.handleRecentMatchesState(it)
                    }
                })
        playerViewModel.fetchRecentMatches(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
    }

    private fun observePlayerFromPlayerViewModel() {
        playerViewModel.getPlayer()
                .observe(this, Observer<PlayerState> {
                    if (it != null) {
                        this.handlePlayerState(it)
                    }
                })
        playerViewModel.fetchPlayer(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
    }

    private fun handlePlayerState(playerState: PlayerState) {
        when (playerState) {
            is PlayerState.Loading -> setUpForPlayerLoading()
            is PlayerState.Success -> setUpForPlayerSuccess(playerState.data!!)
            is PlayerState.Error -> setUpForPlayerError(playerState.errorMessage)
        }
    }

    private fun handleRecentMatchesState(recentMatchesState: RecentMatchesState) {
        when (recentMatchesState) {
            is RecentMatchesState.Loading -> setUpRecentMatchesForLoading()
            is RecentMatchesState.Success -> setUpForRecentMatchesSuccess(recentMatchesState.data!!)
            is RecentMatchesState.Error -> setUpForRecentMatchesError(recentMatchesState.errorMessage)
        }
    }

    private fun setUpViewListeners() {
        error_view.listener = errorListener
        empty_view.listener = emptyListener
    }

    private fun setUpForPlayerLoading() {
        progress.visible()
        error_view.invisible()
        avatar_iv.invisible()
        name_tv.invisible()
    }

    private fun setUpForPlayerError(errorMessage: String?) {
        progress.invisible()
        error_view.visible()
        avatar_iv.invisible()
        name_tv.invisible()

        displayErrorMessage(errorMessage)
    }

    private fun setUpForPlayerSuccess(player: Player) {
        progress.invisible()
        error_view.invisible()
        empty_view.invisible()
        avatar_iv.visible()
        name_tv.visible()

        setPlayerName(player)
        setPlayerAvatar(player)
    }

    private fun setUpRecentMatchesForLoading() {
        progress.visible()
        recent_matches_rv.invisible()
        error_view.invisible()
        empty_view.invisible()
    }

    private fun setUpForRecentMatchesError(errorMessage: String?) {
        error_view.visible()
        recent_matches_rv.invisible()
        empty_view.invisible()
        displayErrorMessage(errorMessage)
    }

    private fun setUpForRecentMatchesSuccess(matches: List<Match>) {
        progress.invisible()
        empty_view.invisible()
        error_view.invisible()

        if (matches.isNotEmpty()) {
            recent_matches_rv.visible()
            matchAdapter.items = matches
            matchAdapter.notifyDataSetChanged()
        } else {
            recent_matches_rv.invisible()
        }
    }

    private fun setPlayerName(player: Player?) {
        name_tv.text = player?.profile?.name
    }

    private fun setPlayerAvatar(player: Player?) {
        Glide.with(this)
                .load(player?.profile?.avatar)
                .into(avatar_iv)
    }

    private val errorListener = object : ErrorListener {
        override fun onRetryClicked() {
            playerViewModel.fetchRecentMatches(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
            playerViewModel.fetchPlayer(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
        }
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            playerViewModel.fetchRecentMatches(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
            playerViewModel.fetchPlayer(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
        }
    }

}
