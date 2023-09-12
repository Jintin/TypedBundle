package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharKeyTest : BaseKeyTest() {

    private val key = CharKey("Test")
    private val expect = 'a'
    private val expectWithDefault = 'b'
    private val defaultValue = 'c'

    @Before
    fun setup() {
        every { bundle.getChar(any()) } returns expect
        every { bundle.getChar(any(), any()) } returns expectWithDefault
        every { intent.getCharExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putChar(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getChar(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getChar(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent, defaultValue)
        verify(exactly = 1) { intent.getCharExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}