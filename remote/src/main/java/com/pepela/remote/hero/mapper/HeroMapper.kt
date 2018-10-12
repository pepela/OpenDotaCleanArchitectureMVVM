package com.pepela.remote.hero.mapper

import com.pepela.data.hero.model.Hero
import com.pepela.remote.EntityMapper
import com.pepela.remote.hero.model.HeroModel

class HeroMapper(private val roleMapper: RoleMapper, private val attributeMapper: AttributeMapper,
                 private val attackTypeMapper: AttackTypeMapper)
    : EntityMapper<HeroModel, Hero> {

    override fun from(model: HeroModel): Hero =
            with(model) {
                Hero(id, name, localizedName, attributeMapper.from(primaryAttribute),
                        attackTypeMapper.from(attackType), roleMapper.from(roles), legs)
            }

}
