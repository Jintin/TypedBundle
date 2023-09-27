package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharSequenceArrayListKeyTest : BaseKeyTest() {
    private val key = CharSequenceArrayListKey("Test")
    private val expect = ArrayList<CharSequence>()

    @Before
    fun setup() {
        every { bundle.getCharSequenceArrayList(any()) } returns expect
        every { intent.getCharSequenceArrayListExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putCharSequenceArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getCharSequenceArrayList(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getCharSequenceArrayListExtra(key.key) }
        assert(result == expect)
    }
}