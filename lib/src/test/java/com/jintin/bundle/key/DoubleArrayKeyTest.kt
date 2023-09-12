package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DoubleArrayKeyTest : BaseKeyTest() {
    private val key = DoubleArrayKey("Test")
    private val expect = DoubleArray(2) { 1.0 }

    @Before
    fun setup() {
        every { bundle.getDoubleArray(any()) } returns expect
        every { intent.getDoubleArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putDoubleArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getDoubleArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getDoubleArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}