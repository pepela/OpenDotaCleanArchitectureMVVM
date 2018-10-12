package com.pepela.remote.hero.mapper

import com.pepela.data.hero.model.AttackType
import com.pepela.remote.EntityMapper
import com.pepela.remote.hero.model.AttackTypeModel

class AttackTypeMapper : EntityMapper<AttackTypeModel, AttackType> {

    override fun from(model: AttackTypeModel): AttackType =
            when (model) {
                AttackTypeModel.MELEE -> AttackType.MELEE
                AttackTypeModel.RANGED -> AttackType.RANGED
            }

}
