package com.pepela.opendota.di

import com.pepela.cache.PreferenceHelper
import com.pepela.cache.hero.HeroCacheImpl
import com.pepela.cache.player.PlayerCacheImpl
import com.pepela.data.HeroDataRepository
import com.pepela.data.MatchDataRepository
import com.pepela.data.PlayerDataRepository
import com.pepela.data.executor.JobExecutor
import com.pepela.data.executor.PostExecutionThread
import com.pepela.data.executor.ThreadExecutor
import com.pepela.data.match.interactor.GetRecentMatchesUseCase
import com.pepela.data.player.interactor.GetPlayerUseCase
import com.pepela.data.player.interactor.SearchProfileUseCase
import com.pepela.data.repository.HeroRepository
import com.pepela.data.repository.MatchRepository
import com.pepela.data.repository.PlayerRepository
import com.pepela.data.source.hero.HeroDataStore
import com.pepela.data.source.hero.HeroDataStoreFactory
import com.pepela.data.source.match.MatchDataStore
import com.pepela.data.source.match.MatchDataStoreFactory
import com.pepela.data.source.player.PlayerDataStore
import com.pepela.data.source.player.PlayerDataStoreFactory
import com.pepela.opendota.BuildConfig
import com.pepela.opendota.UiThread
import com.pepela.opendota.player.MatchAdapter
import com.pepela.opendota.player.PlayerViewModel
import com.pepela.opendota.search.SearchProfileAdapter
import com.pepela.opendota.search.SearchViewModel
import com.pepela.remote.hero.HeroRemoteImpl
import com.pepela.remote.hero.HeroServiceFactory
import com.pepela.remote.hero.mapper.AttackTypeMapper
import com.pepela.remote.hero.mapper.AttributeMapper
import com.pepela.remote.hero.mapper.HeroMapper
import com.pepela.remote.hero.mapper.RoleMapper
import com.pepela.remote.player.MatchRemoteImpl
import com.pepela.remote.player.PlayerRemoteImpl
import com.pepela.remote.player.PlayerServiceFactory
import com.pepela.remote.player.mapper.match.MatchMapper
import com.pepela.remote.player.mapper.match.SideMapper
import com.pepela.remote.player.mapper.player.PlayerMapper
import com.pepela.remote.player.mapper.player.ProfileMapper
import com.pepela.remote.player.mapper.player.RankMapper
import com.pepela.remote.player.mapper.player.SearchProfileMapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module(override = true) {

    single { PreferenceHelper(androidContext()) }

    factory { RankMapper() }
    factory { ProfileMapper() }
    factory { SearchProfileMapper() }
    factory { PlayerMapper(get(), get()) }
    factory { SideMapper() }
    factory { MatchMapper(get()) }
    factory { AttributeMapper() }
    factory { AttackTypeMapper() }
    factory { RoleMapper() }
    factory { HeroMapper(get(), get(), get()) }

    single { JobExecutor() as ThreadExecutor }
    single { UiThread() as PostExecutionThread }

    factory { PlayerServiceFactory.makePlayerService(BuildConfig.DEBUG) }

    factory<PlayerDataStore>(name = "remote") { PlayerRemoteImpl(get(), get(), get()) }
    factory<PlayerDataStore>(name = "local") { PlayerCacheImpl() }
    factory { PlayerDataStoreFactory(get("remote"), get("local")) }
    factory<PlayerRepository> { PlayerDataRepository(get()) }

    factory<MatchDataStore>(name = "remote") { MatchRemoteImpl(get(), get()) }
    factory { MatchDataStoreFactory(get("remote")) }
    factory<MatchRepository> { MatchDataRepository(get()) }


    factory { HeroServiceFactory.makeHeroService(BuildConfig.DEBUG) }

    factory<HeroDataStore>(name = "remote") { HeroRemoteImpl(get(), get()) }
    factory<HeroDataStore>(name = "local") { HeroCacheImpl() }
    factory { HeroDataStoreFactory(get("remote"), get("local")) }
    factory<HeroRepository> { HeroDataRepository(get()) }

}

val playerModule = module("Player", override = true) {
    factory { GetPlayerUseCase(get(), get(), get()) }
    factory { GetRecentMatchesUseCase(get(), get(), get()) }
    factory { MatchAdapter() }
    viewModel { PlayerViewModel(get(), get()) }
}

val searchModule = module("Search", override = true) {
    factory { SearchProfileUseCase(get(), get(), get()) }
    factory { SearchProfileAdapter() }
    viewModel { SearchViewModel(get()) }
}
