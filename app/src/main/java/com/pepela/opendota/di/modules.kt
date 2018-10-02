package com.pepela.opendota.di

import com.pepela.cache.PreferenceHelper
import com.pepela.cache.player.PlayerCacheImpl
import com.pepela.data.MatchDataRepository
import com.pepela.data.PlayerDataRepository
import com.pepela.data.executor.JobExecutor
import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.match.interactor.GetRecentMatchesUseCase
import com.pepela.data.player.interactor.GetPlayerUseCase
import com.pepela.data.repository.MatchRepository
import com.pepela.data.repository.PlayerRepository
import com.pepela.data.source.match.MatchDataStore
import com.pepela.data.source.match.MatchDataStoreFactory
import com.pepela.data.source.player.PlayerDataStore
import com.pepela.data.source.player.PlayerDataStoreFactory
import com.pepela.opendota.BuildConfig
import com.pepela.opendota.UiThread
import com.pepela.opendota.player.PlayerViewModel
import com.pepela.remote.player.MatchRemoteImpl
import com.pepela.remote.player.PlayerRemoteImpl
import com.pepela.remote.player.PlayerServiceFactory
import com.pepela.remote.player.mapper.match.MatchMapper
import com.pepela.remote.player.mapper.match.SideMapper
import com.pepela.remote.player.mapper.player.PlayerMapper
import com.pepela.remote.player.mapper.player.ProfileMapper
import com.pepela.remote.player.mapper.player.RankMapper
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module(override = true) {

    factory { RankMapper() }
    factory { ProfileMapper() }
    factory { PlayerMapper(get(), get()) }
    factory { SideMapper() }
    factory { MatchMapper(get()) }

    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }

    factory { PlayerServiceFactory.makePlayerService(BuildConfig.DEBUG) }

    factory<PlayerDataStore>(name = "remote") { PlayerRemoteImpl(get(), get()) }
    factory<PlayerDataStore>(name = "local") { PlayerCacheImpl() }
    factory { PlayerDataStoreFactory(get("remote"), get("local")) }
    factory<PlayerRepository> { PlayerDataRepository(get()) }

    factory<MatchDataStore>(name = "remote") { MatchRemoteImpl(get(), get()) }
    factory { MatchDataStoreFactory(get("remote")) }
    factory<MatchRepository> { MatchDataRepository(get()) }

    single { PreferenceHelper(androidContext()) }

}

val playerModule = module("Player", override = true) {
    factory { GetPlayerUseCase(get(), get(), get()) }
    factory { GetRecentMatchesUseCase(get(), get(), get()) }
    viewModel { PlayerViewModel(get(), get()) }
}
