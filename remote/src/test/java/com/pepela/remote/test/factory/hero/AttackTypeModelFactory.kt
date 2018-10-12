package com.pepela.remote.test.factory.hero

import com.pepela.remote.hero.model.AttackTypeModel

class AttackTypeModelFactory {

    companion object Factory {

        fun randomAttackTypeModel() = AttackTypeModel.values().toList().shuffled().first()

    }

}
