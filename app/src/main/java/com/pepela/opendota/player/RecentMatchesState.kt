package com.pepela.opendota.player

import com.pepela.data.match.model.Match
import com.pepela.opendota.model.ResourceState

sealed class RecentMatchesState(val state: ResourceState,
                                val data: List<Match>? = null,
                                val errorMessage: String? = null) {

    data class Success(private val matches: List<Match>)
        : RecentMatchesState(ResourceState.SUCCESS, matches)

    data class Error(private val message: String? = null)
        : RecentMatchesState(ResourceState.ERROR, errorMessage = message)

    object Loading : RecentMatchesState(ResourceState.LOADING)

}
