package com.pepela.remote.test.factory.player

import com.pepela.remote.player.model.player.ProfileModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong
import com.pepela.remote.test.factory.DataFactory.Factory.randomUuid

class ProfileModelFactory {

    companion object Factory {

        fun makeProfileModel() = ProfileModel(randomUuid(), randomLong(), randomUuid(), randomUuid())

    }

}
