package com.pepela.remote.player.mapper.player

import com.pepela.data.player.model.Profile
import com.pepela.remote.EntityMapper
import com.pepela.remote.player.model.player.ProfileModel

open class ProfileMapper : EntityMapper<ProfileModel, Profile> {

    override fun from(model: ProfileModel): Profile =
            with(model) {
                Profile(name, accountId, avatar, steamProfileUrl)
            }

}
