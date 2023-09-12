package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class FloatKeyTest : BaseKeyTest() {

    private val key = FloatKey("Test")
    private val expect = 1234f
    private val expectWithDefault = 2345f
    private val defaultValue = 3456f

    @Before
    fun setup() {
        every { bundle.getFloat(any()) } returns expect
        every { bundle.getFloat(any(), any()) } returns expectWithDefault
        every { intent.getFloatExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putFloat(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getFloat(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getFloat(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent, defaultValue)
        verify(exactly = 1) { intent.getFloatExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}