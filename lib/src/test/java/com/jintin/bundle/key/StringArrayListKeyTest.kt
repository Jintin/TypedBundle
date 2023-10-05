package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class StringArrayListKeyTest : BaseKeyTest() {
    private val key = StringArrayListKey("Test")
    private val expect = ArrayList<String>()

    @Before
    fun setup() {
        every { bundle.getStringArrayList(any()) } returns expect
        every { intent.getStringArrayListExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putStringArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putStringArrayListExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getStringArrayList(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getStringArrayListExtra(key.key) }
        assert(result == expect)
    }
}