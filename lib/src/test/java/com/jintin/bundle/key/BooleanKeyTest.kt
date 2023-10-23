package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BooleanKeyTest : BaseKeyTest() {

    private val key = BooleanKey("Test")
    private val expect = true
    private val expectWithDefault = false
    private val defaultValue = true

    @Before
    fun setup() {
        every { bundle.getBoolean(any()) } returns expect
        every { bundle.getBoolean(any(), any()) } returns expectWithDefault
        every { persistableBundle.getBoolean(any()) } returns expect
        every { persistableBundle.getBoolean(any(), any()) } returns expectWithDefault
        every { intent.getBooleanExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putBoolean(key.key, expect) }
    }

    @Test
    fun putPersistTest() {
        persistableBundle[key] = expect
        verify(exactly = 1) { persistableBundle.putBoolean(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getBoolean(key.key) }
        assert(result == expect)
    }

    @Test
    fun getPersistTest() {
        val result = persistableBundle[key]
        verify(exactly = 1) { persistableBundle.getBoolean(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = bundle.get(key, defaultValue)
        verify(exactly = 1) { bundle.getBoolean(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getPersistWithDefaultTest() {
        val result = persistableBundle.get(key, defaultValue)
        verify(exactly = 1) { persistableBundle.getBoolean(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key, defaultValue)
        verify(exactly = 1) { intent.getBooleanExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}