package com.pepela.data.test.factory.player

import com.pepela.data.player.model.Profile
import com.pepela.data.test.factory.DataFactory.Factory.randomLong
import com.pepela.data.test.factory.DataFactory.Factory.randomUuid

class ProfileFactory {

    companion object Factory {

        fun makeProfile() = Profile(randomUuid(), randomLong(), randomUuid(), randomUuid())

    }

}
