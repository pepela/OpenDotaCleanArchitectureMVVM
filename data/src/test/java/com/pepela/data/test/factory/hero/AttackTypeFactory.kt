package com.pepela.data.test.factory.hero

import com.pepela.data.hero.model.AttackType


class AttackTypeFactory {

    companion object Factory {

        fun randomAttackType() = AttackType.values().toList().shuffled().first()

    }

}
