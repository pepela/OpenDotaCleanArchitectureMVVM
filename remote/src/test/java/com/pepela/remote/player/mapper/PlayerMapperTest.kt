package com.pepela.remote.player.mapper

import com.pepela.remote.player.mapper.player.PlayerMapper
import com.pepela.remote.player.mapper.player.ProfileMapper
import com.pepela.remote.player.mapper.player.RankMapper
import com.pepela.remote.test.factory.player.PlayerModelFactory.Factory.makePlayerModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PlayerMapperTest {

    val rankMapper = RankMapper()
    val profileMapper = ProfileMapper()

    val playerMapper = PlayerMapper(rankMapper, profileMapper)

    @Test
    fun map_from_remote_maps_data() {
        val model = makePlayerModel()
        val entity = playerMapper.from(model)

        assertEquals(rankMapper.from(model.rank), entity.rank)
        assertEquals(profileMapper.from(model.profile), entity.profile)
        assertEquals(model.leaderBoardRank, entity.leaderBoardRank)
    }

}
