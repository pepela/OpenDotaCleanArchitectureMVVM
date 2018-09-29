package com.pepela.remote.player.mapper

import com.pepela.remote.player.test.factory.ProfileModelFactory.Factory.makeProfileModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class ProfileMapperTest {

    private val profileMapper = ProfileMapper()

    @Test
    fun map_from_remote_maps_data() {
        val model = makeProfileModel()
        val entity = profileMapper.from(model)

        assertEquals(model.accountId, entity.accountId)
        assertEquals(model.name, entity.name)
        assertEquals(model.avatar, entity.avatar)
        assertEquals(model.steamProfileUrl, entity.steamProfileUrl)
    }

}
