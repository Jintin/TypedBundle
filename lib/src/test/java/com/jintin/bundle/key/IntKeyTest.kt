package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class IntKeyTest : BaseKeyTest() {

    private val key = IntKey("Test")
    private val expect = 1234
    private val expectWithDefault = 2345
    private val defaultValue = 3456

    @Before
    fun setup() {
        every { bundle.getInt(any()) } returns expect
        every { bundle.getInt(any(), any()) } returns expectWithDefault
        every { intent.getIntExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putInt(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getInt(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = bundle.get(key, defaultValue)
        verify(exactly = 1) { bundle.getInt(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key, defaultValue)
        verify(exactly = 1) { intent.getIntExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}