package com.pepela.opendota.search

import com.pepela.data.player.model.SearchProfile
import com.pepela.opendota.model.ResourceState


sealed class SearchState(val resourceState: ResourceState,
                         val data: List<SearchProfile>? = null,
                         val errorMessage: String? = null) {

    data class Success(val profiles: List<SearchProfile>) :
            SearchState(ResourceState.SUCCESS, profiles)

    data class Error(val message: String?) : SearchState(ResourceState.ERROR, errorMessage = message)

    object Loading : SearchState(ResourceState.LOADING)
}
