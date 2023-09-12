package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FloatArrayKeyTest : BaseKeyTest() {
    private val key = FloatArrayKey("Test")
    private val expect = FloatArray(2) { 1f }

    @Before
    fun setup() {
        every { bundle.getFloatArray(any()) } returns expect
        every { intent.getFloatArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putFloatArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getFloatArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getFloatArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}