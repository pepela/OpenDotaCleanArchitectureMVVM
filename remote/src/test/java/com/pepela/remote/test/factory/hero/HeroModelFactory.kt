package com.pepela.remote.test.factory.hero

import com.pepela.remote.hero.model.HeroModel
import com.pepela.remote.test.factory.DataFactory.Factory.randomInt
import com.pepela.remote.test.factory.DataFactory.Factory.randomLong
import com.pepela.remote.test.factory.DataFactory.Factory.randomUuid
import com.pepela.remote.test.factory.hero.AttackTypeModelFactory.Factory.randomAttackTypeModel
import com.pepela.remote.test.factory.hero.AttributeModelFactory.Factory.randomAttributeModel
import com.pepela.remote.test.factory.hero.RoleModelFactory.Factory.randomRoleModels

class HeroModelFactory {

    companion object Factory {

        fun makeHeroModel() =
                HeroModel(randomLong(), randomUuid(), randomUuid(), randomAttributeModel(),
                        randomAttackTypeModel(), randomRoleModels(2), randomInt())

        fun makeHeroModels(count: Int) = MutableList(count) { makeHeroModel() }

    }

}
