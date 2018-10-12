package com.pepela.remote.player.mapper.player

import com.pepela.data.player.model.SearchProfile
import com.pepela.remote.EntityMapper
import com.pepela.remote.player.model.player.SearchProfileModel

class SearchProfileMapper : EntityMapper<SearchProfileModel, SearchProfile> {

    override fun from(model: SearchProfileModel): SearchProfile =
            with(model) {
                SearchProfile(name, accountId, avatar, lastPlayed)
            }

}
