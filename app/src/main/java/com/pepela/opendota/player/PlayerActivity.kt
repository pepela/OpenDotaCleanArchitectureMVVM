package com.pepela.opendota.player

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.bumptech.glide.Glide
import com.pepela.data.player.model.Player
import com.pepela.opendota.R
import com.pepela.opendota.widget.error.ErrorListener
import kotlinx.android.synthetic.main.activity_player.*
import org.koin.android.scope.ext.android.bindScope
import org.koin.android.scope.ext.android.getCurrentScope
import org.koin.android.viewmodel.ext.android.viewModel

class PlayerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_ID = "extra_account_id"
    }

    val playerViewModel: PlayerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        bindScope(getCurrentScope())

        setUpViewListeners()

        playerViewModel.getPlayer()
                .observe(this, Observer<PlayerState> {
                    if (it != null) {
                        this.handleDataState(it)
                    }
                })
        playerViewModel.fetchPlayer(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
    }

    private fun handleDataState(playerState: PlayerState) {
        when (playerState) {
            is PlayerState.Loading -> setUpForLoading()
            is PlayerState.Success -> setUpForSuccess(playerState.data)
            is PlayerState.Error -> setUpForError(playerState.errorMessage)
        }
    }

    private fun setUpViewListeners() {
        error_view.listener = errorListener
    }

    private fun setUpForLoading() {
        progress.visibility = VISIBLE
        error_view.visibility = GONE
        avatar_iv.visibility = GONE
        name_tv.visibility = GONE
    }

    private fun setUpForError(errorMessage: String?) {
        progress.visibility = GONE
        error_view.visibility = VISIBLE
        avatar_iv.visibility = GONE
        name_tv.visibility = GONE

        displayErroMessage(errorMessage)
    }

    private fun setUpForSuccess(player: Player?) {
        progress.visibility = GONE
        error_view.visibility = GONE
        avatar_iv.visibility = VISIBLE
        name_tv.visibility = VISIBLE

        setPlayerName(player)
        setPlayerAvatar(player)
    }

    private fun displayErroMessage(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
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
            playerViewModel.fetchPlayer(intent.getLongExtra(EXTRA_ACCOUNT_ID, 0))
        }
    }

}
