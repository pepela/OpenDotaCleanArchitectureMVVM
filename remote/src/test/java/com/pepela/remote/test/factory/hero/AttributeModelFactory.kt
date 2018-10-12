package com.pepela.remote.test.factory.hero

import com.pepela.remote.hero.model.AttributeModel

class AttributeModelFactory {

    companion object Factory {

        fun randomAttributeModel() = AttributeModel.values().toList().shuffled().first()

    }

}
