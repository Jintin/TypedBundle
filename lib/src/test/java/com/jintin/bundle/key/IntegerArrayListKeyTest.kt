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
        bundle[key] = expect
        verify(exactly = 1) { bundle.putIntegerArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getIntegerArrayList(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getIntegerArrayListExtra(key.key) }
        assert(result == expect)
    }
}