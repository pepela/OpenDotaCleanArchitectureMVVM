package com.pepela.opendota.player

import com.pepela.data.player.model.Player
import com.pepela.opendota.model.ResourceState

sealed class PlayerState(var resourceState: ResourceState,
                         var data: Player? = null,
                         var errorMessage: String? = null) {

    data class Success(private val player: Player) : PlayerState(ResourceState.SUCCESS, player)

    data class Error(private val message: String? = null) :
            PlayerState(ResourceState.ERROR, errorMessage = message)

    object Loading : PlayerState(ResourceState.LOADING)

}
