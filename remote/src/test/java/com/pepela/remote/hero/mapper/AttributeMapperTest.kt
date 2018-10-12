package com.pepela.remote.hero.mapper

import com.pepela.remote.test.factory.hero.AttributeModelFactory.Factory.randomAttributeModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class AttributeMapperTest {

    private val attributeMapper = AttributeMapper()

    @Test
    fun map_from_remote_maps_data() {
        val model = randomAttributeModel()
        val entity = attributeMapper.from(model)

        assertEquals(model.name, entity.name)
    }

}
