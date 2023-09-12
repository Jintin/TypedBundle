package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DoubleKeyTest : BaseKeyTest() {

    private val key = DoubleKey("Test")
    private val expect = 1234.0
    private val expectWithDefault = 2345.0
    private val defaultValue = 3456.0

    @Before
    fun setup() {
        every { bundle.getDouble(any()) } returns expect
        every { bundle.getDouble(any(), any()) } returns expectWithDefault
        every { intent.getDoubleExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putDouble(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getDouble(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getDouble(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent, defaultValue)
        verify(exactly = 1) { intent.getDoubleExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}