package com.pepela.remote.hero.mapper

import com.pepela.remote.test.factory.hero.HeroModelFactory.Factory.makeHeroModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class HeroMapperTest {

    private val roleMapper = RoleMapper()
    private val attackTypeMapper = AttackTypeMapper()
    private val attributeMapper = AttributeMapper()
    private val heroMapper = HeroMapper(roleMapper, attributeMapper, attackTypeMapper)

    @Test
    fun map_from_remote_maps_data() {
        val model = makeHeroModel()
        val entity = heroMapper.from(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.name, entity.name)
        assertEquals(model.localizedName, entity.localizedName)
        assertEquals(model.legs, entity.legs)
        assertEquals(attributeMapper.from(model.primaryAttribute), entity.primaryAttribute)
        assertEquals(roleMapper.from(model.roles), entity.roles)
        assertEquals(attackTypeMapper.from(model.attackType), entity.attackType)
    }

}
