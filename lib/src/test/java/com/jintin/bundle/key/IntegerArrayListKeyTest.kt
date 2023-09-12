package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class IntegerArrayListKeyTest : BaseKeyTest() {
    private val key = IntegerArrayListKey("Test")
    private val expect = ArrayList<Int>()

    @Before
    fun setup() {
        every { bundle.getIntegerArrayList(any()) } returns expect
        every { intent.getIntegerArrayListExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putIntegerArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getIntegerArrayList(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getIntegerArrayListExtra(key.key) }
        assert(result == expect)
    }
}