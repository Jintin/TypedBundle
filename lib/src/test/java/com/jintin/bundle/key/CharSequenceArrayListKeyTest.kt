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
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putCharSequenceArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getCharSequenceArrayList(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getCharSequenceArrayListExtra(key.key) }
        assert(result == expect)
    }
}