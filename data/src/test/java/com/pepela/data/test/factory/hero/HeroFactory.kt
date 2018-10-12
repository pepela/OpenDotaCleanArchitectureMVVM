package com.pepela.data.test.factory.hero

import com.pepela.data.hero.model.Hero
import com.pepela.data.test.factory.DataFactory.Factory.randomInt
import com.pepela.data.test.factory.DataFactory.Factory.randomLong
import com.pepela.data.test.factory.DataFactory.Factory.randomUuid
import com.pepela.data.test.factory.hero.AttackTypeFactory.Factory.randomAttackType
import com.pepela.data.test.factory.hero.AttributeFactory.Factory.randomAttribute
import com.pepela.data.test.factory.hero.RoleFactory.Factory.randomRoles

class HeroFactory {

    companion object Factory {

        fun makeHero() =
                Hero(randomLong(), randomUuid(), randomUuid(), randomAttribute(),
                        randomAttackType(), randomRoles(2), randomInt())

        fun makeHeroes(count: Int) = MutableList(count) { makeHero() }

    }

}
