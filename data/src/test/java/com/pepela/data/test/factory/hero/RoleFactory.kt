package com.pepela.data.test.factory.hero

import com.pepela.data.hero.model.Role

class RoleFactory {

    companion object Factory {

        fun randomRole() = Role.values().toList().shuffled().first()

        fun randomRoles(count: Int) = MutableList(count) { randomRole() }

    }

}
