package com.pepela.remote.test.factory.match

import com.pepela.remote.player.model.match.SideModel

class SideModelFactory {

    companion object Factory {

        fun randomSideModel() = SideModel.values().toList().shuffled().first()

    }

}
