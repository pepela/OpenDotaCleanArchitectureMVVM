package com.pepela.remote.hero.mapper

import com.pepela.remote.test.factory.hero.AttackTypeModelFactory.Factory.randomAttackTypeModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class AttackTypeMapperTest {

    private val attackTypeMapper = AttackTypeMapper()

    @Test
    fun map_from_remote_maps_data() {
        val model = randomAttackTypeModel()
        val entity = attackTypeMapper.from(model)

        assertEquals(model.name, entity.name)
    }

}
