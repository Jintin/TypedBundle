package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ShortArrayKeyTest : BaseKeyTest() {
    private val key = ShortArrayKey("Test")
    private val expect = ShortArray(2) { 1 }

    @Before
    fun setup() {
        every { bundle.getShortArray(any()) } returns expect
        every { intent.getShortArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putShortArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getShortArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getShortArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}