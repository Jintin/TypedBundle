package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharArrayKeyTest : BaseKeyTest() {
    private val key = CharArrayKey("Test")
    private val expect = CharArray(2)

    @Before
    fun setup() {
        every { bundle.getCharArray(any()) } returns expect
        every { intent.getCharArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putCharArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getCharArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getCharArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}