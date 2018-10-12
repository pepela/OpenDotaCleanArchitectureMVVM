package com.pepela.remote.hero.mapper

import com.pepela.remote.test.factory.hero.RoleModelFactory.Factory.randomRoleModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class RoleMapperTest {

    private val roleMapper = RoleMapper()

    @Test
    fun map_from_remote_maps_data() {
        val model = randomRoleModel()
        val entity = roleMapper.from(model)

        assertEquals(model.name, entity.name)
    }

}
