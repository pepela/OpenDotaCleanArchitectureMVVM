package com.pepela.data.test.factory.hero

import com.pepela.data.hero.model.Attribute

class AttributeFactory {

    companion object Factory {

        fun randomAttribute() = Attribute.values().toList().shuffled().first()

    }

}
