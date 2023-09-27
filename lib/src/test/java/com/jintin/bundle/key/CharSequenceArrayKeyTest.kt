package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharSequenceArrayKeyTest : BaseKeyTest() {
    private val key = CharSequenceArrayKey("Test")
    private val expect = Array<CharSequence>(2) { "AA" }

    @Before
    fun setup() {
        every { bundle.getCharSequenceArray(any()) } returns expect
        every { intent.getCharSequenceArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putCharSequenceArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getCharSequenceArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getCharSequenceArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}