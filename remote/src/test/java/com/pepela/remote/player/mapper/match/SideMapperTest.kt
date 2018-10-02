package com.pepela.remote.player.mapper.match

import com.pepela.remote.test.factory.match.SideModelFactory.Factory.randomSideModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class SideMapperTest {

    private val sideMapper = SideMapper()

    @Test
    fun map_from_remote_maps_data() {
        val model = randomSideModel()
        val entity = sideMapper.from(model)

        assertEquals(model.name, entity.name)
    }

}
