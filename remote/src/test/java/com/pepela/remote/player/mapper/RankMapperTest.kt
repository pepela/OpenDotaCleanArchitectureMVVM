package com.pepela.remote.player.mapper

import com.pepela.remote.player.test.factory.RankModelFactory.Factory.randomRankModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class RankMapperTest {

    private val rankMapper = RankMapper()

    @Test
    fun map_from_remote_maps_data() {
        val model = randomRankModel()
        val entity = rankMapper.from(model)
        assertEquals(model.name, entity.name)
    }

}
