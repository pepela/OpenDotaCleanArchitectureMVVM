package com.pepela.remote.player.test.factory

import com.pepela.remote.player.model.ProfileModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong
import com.pepela.remote.test.factory.DataFactory.Factory.randomUuid

class ProfileModelFactory {

    companion object Factory {

        fun makeProfileModel() = ProfileModel(randomUuid(), randomLong(), randomUuid(), randomUuid())

    }

}
