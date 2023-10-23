package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LongArrayKeyTest : BaseKeyTest() {
    private val key = LongArrayKey("Test")
    private val expect = LongArray(2) { 2 }

    @Before
    fun setup() {
        every { bundle.getLongArray(any()) } returns expect
        every { persistableBundle.getLongArray(any()) } returns expect
        every { intent.getLongArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putLongArray(key.key, expect) }
    }

    @Test
    fun putPersistTest() {
        persistableBundle[key] = expect
        verify(exactly = 1) { persistableBundle.putLongArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getLongArray(key.key) }
        assert(result.contentEquals(expect))
    }
    @Test
    fun getPersistTest() {
        val result = persistableBundle[key]
        verify(exactly = 1) { persistableBundle.getLongArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getLongArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}