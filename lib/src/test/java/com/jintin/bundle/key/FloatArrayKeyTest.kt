package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FloatArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = FloatArrayKey("Test")
    private val expect = FloatArray(2) { 1f }

    @Before
    fun setup() {
        every { bundle.getFloatArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putFloatArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getFloatArray(key.key) }
        assert(result.contentEquals(expect))
    }
}