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
        every { persistableBundle.getBooleanArray(any()) } returns expect
        every { intent.getBooleanArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putBooleanArray(key.key, expect) }
    }

    @Test
    fun putPersistTest() {
        persistableBundle[key] = expect
        verify(exactly = 1) { persistableBundle.putBooleanArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getBooleanArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getPersistTest() {
        val result = persistableBundle[key]
        verify(exactly = 1) { persistableBundle.getBooleanArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getBooleanArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}