package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharSequenceKeyTest : BaseKeyTest() {

    private val key = CharSequenceKey("Test")
    private val expect: CharSequence = "1234"

    @Before
    fun setup() {
        every { bundle.getCharSequence(any()) } returns expect
        every { intent.getCharSequenceExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putCharSequence(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getCharSequence(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getCharSequenceExtra(key.key) }
        assert(result == expect)
    }
}