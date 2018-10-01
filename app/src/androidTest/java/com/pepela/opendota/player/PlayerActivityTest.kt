package com.pepela.opendota.player

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.pepela.data.repository.PlayerRepository
import com.pepela.opendota.R
import com.pepela.opendota.di.applicationModule
import com.pepela.opendota.di.playerModule
import com.pepela.opendota.test.factory.PlayerFactory.Factory.makePlayer
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class PlayerActivityTest : KoinTest {

    companion object {
        private const val PLAYER_ID = 1L
    }

    val mockPlayerRepository: PlayerRepository by inject()

    @Rule
    @JvmField
    val activity = ActivityTestRule<PlayerActivity>(PlayerActivity::class.java, false, false)

    @Before
    fun setUp() {
        loadKoinModules(applicationModule, playerModule)
    }

    @Test
    fun activity_launches() {
        whenever(mockPlayerRepository.getPlayer(any())).thenReturn(Flowable.just(makePlayer()))

        activity.launchActivity(makeStartIntent())
    }

    @Test
    fun avatar_display() {
        val player = makePlayer()
        whenever(mockPlayerRepository.getPlayer(any())).thenReturn(Flowable.just(player))
        activity.launchActivity(makeStartIntent())

        onView(withId(R.id.avatar_iv)).check(matches(isDisplayed()))
    }

    @Test
    fun name_display() {
        val player = makePlayer()
        whenever(mockPlayerRepository.getPlayer(any())).thenReturn(Flowable.just(player))
        activity.launchActivity(makeStartIntent())

        onView(withId(R.id.name_tv)).check(matches(isDisplayed()))
    }

    private fun makeStartIntent(): Intent {
        val startIntent = Intent()
        startIntent.putExtra(PlayerActivity.EXTRA_ACCOUNT_ID, PLAYER_ID)
        return startIntent
    }
}