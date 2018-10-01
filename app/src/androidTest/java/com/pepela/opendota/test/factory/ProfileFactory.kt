package com.pepela.opendota.test.factory

import com.pepela.data.player.model.Profile
import com.pepela.opendota.test.factory.DataFactory.Factory.randomLong
import com.pepela.opendota.test.factory.DataFactory.Factory.randomUuid

class ProfileFactory {

    companion object Factory {

        fun makeProfile() = Profile(randomUuid(), randomLong(), randomUuid(), randomUuid())

    }

}
