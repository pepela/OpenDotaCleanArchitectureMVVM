package com.pepela.remote.player.mapper.player

import com.pepela.remote.test.factory.player.SearchProfileFactory.Factory.makeSearchProfileModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchProfileMapperTest {

    private val searchProfileMapper = SearchProfileMapper()

    @Test
    fun map_from_remote_maps_data() {
        val model = makeSearchProfileModel()
        val entity = searchProfileMapper.from(model)

        assertEquals(entity.accountId, model.accountId)
        assertEquals(entity.name, model.name)
        assertEquals(entity.avatar, model.avatar)
        assertEquals(entity.lastPlayed, model.lastPlayed)
    }

}
