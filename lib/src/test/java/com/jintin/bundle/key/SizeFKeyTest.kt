package com.jintin.bundle.key

import android.os.Bundle
import android.util.SizeF
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SizeFKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = SizeFKey("Test")
    private val expect = mockk<SizeF>()

    @Before
    fun setup() {
        every { bundle.getSizeF(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putSizeF(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getSizeF(key.key) }
        assert(result == expect)
    }
}