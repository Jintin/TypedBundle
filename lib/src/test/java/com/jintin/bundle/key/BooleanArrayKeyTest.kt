package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BooleanArrayKeyTest : BaseKeyTest() {
    private val key = BooleanArrayKey("Test")
    private val expect = BooleanArray(2)

    @Before
    fun setup() {
        every { bundle.getBooleanArray(any()) } returns expect
        every { intent.getBooleanArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putBooleanArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getBooleanArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getBooleanArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}