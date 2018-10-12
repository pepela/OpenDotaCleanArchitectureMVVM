package com.pepela.remote.test.factory.hero

import com.pepela.remote.hero.model.RoleModel

class RoleModelFactory {

    companion object Factory {

        fun randomRoleModel() = RoleModel.values().toList().shuffled().first()

        fun randomRoleModels(count: Int) = MutableList(count) { randomRoleModel() }

    }

}
