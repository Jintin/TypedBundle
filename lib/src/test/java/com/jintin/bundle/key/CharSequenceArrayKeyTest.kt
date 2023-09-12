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
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putCharSequenceArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getCharSequenceArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getCharSequenceArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}