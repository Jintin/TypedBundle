package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharSequenceKeyTest : BaseKeyTest() {

    private val key = CharSequenceKey("Test")
    private val expect: CharSequence = "1234"
    private val defaultValue: CharSequence = "ABCD"
    private val expectWithDefault = "EFGH"

    @Before
    fun setup() {
        every { bundle.getCharSequence(any()) } returns expect
        every { bundle.getCharSequence(any(), any()) } returns expectWithDefault
        every { intent.getCharSequenceExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putCharSequence(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getCharSequence(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = bundle.get(key, defaultValue)
        verify(exactly = 1) { bundle.getCharSequence(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getCharSequenceExtra(key.key) }
        assert(result == expect)
    }
}