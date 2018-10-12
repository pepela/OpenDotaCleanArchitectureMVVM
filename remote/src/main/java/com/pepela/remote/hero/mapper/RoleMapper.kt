package com.pepela.remote.hero.mapper

import com.pepela.data.hero.model.Role
import com.pepela.remote.EntityMapper
import com.pepela.remote.hero.model.RoleModel

class RoleMapper : EntityMapper<RoleModel, Role> {

    override fun from(model: RoleModel): Role {
        return when (model) {
            RoleModel.CARRY -> Role.CARRY
            RoleModel.DISABLER -> Role.DISABLER
            RoleModel.ESCAPE -> Role.ESCAPE
            RoleModel.NUKER -> Role.NUKER
            RoleModel.INITIATOR -> Role.INITIATOR
            RoleModel.JUNGLER -> Role.JUNGLER
            RoleModel.DURABLE -> Role.DURABLE
            RoleModel.SUPPORT -> Role.SUPPORT
            RoleModel.PUSHER -> Role.PUSHER
        }
    }

}
