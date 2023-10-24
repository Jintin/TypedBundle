package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class IntArrayKeyTest : BaseKeyTest() {
    private val key = IntArrayKey("Test")
    private val expect = IntArray(2) { 2 }

    @Before
    fun setup() {
        every { bundle.getIntArray(any()) } returns expect
        every { persistableBundle.getIntArray(any()) } returns expect
        every { intent.getIntArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putIntArray(key.key, expect) }
    }

    @Test
    fun putPersistTest() {
        persistableBundle[key] = expect
        verify(exactly = 1) { persistableBundle.putIntArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getIntArray(key.key) }
        assert(result.contentEquals(expect))
    }
    @Test
    fun getPersistTest() {
        val result = persistableBundle[key]
        verify(exactly = 1) { persistableBundle.getIntArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getIntArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}