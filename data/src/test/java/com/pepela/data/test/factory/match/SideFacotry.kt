package com.pepela.data.test.factory.match

import com.pepela.data.match.model.Side

class SideFacotry {

    companion object Factory {

        fun randomSide() = Side.values().toList().shuffled().first()

    }

}
