package com.pepela.cache.player

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PlayerCacheImplTest {

    private val playerCacheImpl = PlayerCacheImpl()

    @Test
    fun isCached_always_returns_false() {
        val testObserver = playerCacheImpl.isCached().test()
        testObserver.assertValue(false)
    }

}
