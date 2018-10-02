package com.pepela.remote.player.mapper

import com.pepela.remote.player.mapper.match.MatchMapper
import com.pepela.remote.player.mapper.match.SideMapper
import com.pepela.remote.test.factory.match.MatchModelFactory.Factory.makeMatchModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MatchMapperTest {

    private val sideMapper = SideMapper()
    private val matchMapper = MatchMapper(sideMapper)

    @Test
    fun map_from_remote_maps_data() {
        val model = makeMatchModel()
        val entity = matchMapper.from(model)

        assertEquals(model.id, entity.id)
        assertEquals(sideMapper.from(model.playerSlot), entity.side)
        assertEquals(model.radiantWin, entity.radiantWin)
        assertEquals(model.duration, entity.duration)
        assertEquals(model.heroId, entity.heroId)
        assertEquals(Date(model.startTime), entity.startTime)
        assertEquals(model.kills, entity.kills)
        assertEquals(model.deaths, entity.deaths)
        assertEquals(model.assists, entity.assists)
        assertEquals(model.xpPerMinute, entity.xpPerMinute)
        assertEquals(model.goldPerMinute, entity.goldPerMinute)
        assertEquals(model.heroDamage, entity.heroDamage)
        assertEquals(model.towerDamage, entity.towerDamage)
        assertEquals(model.heroHealing, entity.heroHealing)
        assertEquals(model.lastHits, entity.lastHits)

    }

}
