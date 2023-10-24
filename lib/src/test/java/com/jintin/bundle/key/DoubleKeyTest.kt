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
        every { persistableBundle.getDouble(any()) } returns expect
        every { persistableBundle.getDouble(any(), any()) } returns expectWithDefault
        every { intent.getDoubleExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putDouble(key.key, expect) }
    }

    @Test
    fun putPersistTest() {
        persistableBundle[key] = expect
        verify(exactly = 1) { persistableBundle.putDouble(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getDouble(key.key) }
        assert(result == expect)
    }

    @Test
    fun getPersistTest() {
        val result = persistableBundle[key]
        verify(exactly = 1) { persistableBundle.getDouble(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = bundle.get(key, defaultValue)
        verify(exactly = 1) { bundle.getDouble(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getPersistWithDefaultTest() {
        val result = persistableBundle.get(key, defaultValue)
        verify(exactly = 1) { persistableBundle.getDouble(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key, defaultValue)
        verify(exactly = 1) { intent.getDoubleExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}