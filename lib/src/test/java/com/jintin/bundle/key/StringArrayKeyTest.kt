package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class StringArrayKeyTest : BaseKeyTest() {
    private val key = StringArrayKey("Test")
    private val expect = Array(2) { "Test$it" }

    @Before
    fun setup() {
        every { bundle.getStringArray(any()) } returns expect
        every { persistableBundle.getStringArray(any()) } returns expect
        every { intent.getStringArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putStringArray(key.key, expect) }
    }

    @Test
    fun putPersistTest() {
        persistableBundle[key] = expect
        verify(exactly = 1) { persistableBundle.putStringArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getStringArray(key.key) }
        assert(result.contentEquals(expect))
    }
    @Test
    fun getPersistTest() {
        val result = persistableBundle[key]
        verify(exactly = 1) { persistableBundle.getStringArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getStringArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}