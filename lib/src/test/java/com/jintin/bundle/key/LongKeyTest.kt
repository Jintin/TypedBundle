package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LongKeyTest : BaseKeyTest() {

    private val key = LongKey("Test")
    private val expect = 1234L
    private val expectWithDefault = 2345L
    private val defaultValue = 3456L

    @Before
    fun setup() {
        every { bundle.getLong(any()) } returns expect
        every { bundle.getLong(any(), any()) } returns expectWithDefault
        every { persistableBundle.getLong(any()) } returns expect
        every { persistableBundle.getLong(any(), any()) } returns expectWithDefault
        every { intent.getLongExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putLong(key.key, expect) }
    }

    @Test
    fun putPersistTest() {
        persistableBundle[key] = expect
        verify(exactly = 1) { persistableBundle.putLong(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getLong(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = bundle.get(key, defaultValue)
        verify(exactly = 1) { bundle.getLong(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getPersistTest() {
        val result = persistableBundle[key]
        verify(exactly = 1) { persistableBundle.getLong(key.key) }
        assert(result == expect)
    }

    @Test
    fun getPersistWithDefaultTest() {
        val result = persistableBundle.get(key, defaultValue)
        verify(exactly = 1) { persistableBundle.getLong(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key, defaultValue)
        verify(exactly = 1) { intent.getLongExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}