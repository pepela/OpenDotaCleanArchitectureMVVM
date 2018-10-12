package com.pepela.remote.hero.mapper

import com.pepela.data.hero.model.Attribute
import com.pepela.remote.EntityMapper
import com.pepela.remote.hero.model.AttributeModel

class AttributeMapper : EntityMapper<AttributeModel, Attribute> {

    override fun from(model: AttributeModel): Attribute =
            when (model) {
                AttributeModel.AGILITY -> Attribute.AGILITY
                AttributeModel.STRENGTH -> Attribute.STRENGTH
                AttributeModel.INTELLIGENCE -> Attribute.INTELLIGENCE
            }

}
