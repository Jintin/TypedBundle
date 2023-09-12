package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ShortKeyTest : BaseKeyTest() {

    private val key = ShortKey("Test")
    private val expect = 1.toShort()
    private val expectWithDefault = 2.toShort()
    private val defaultValue = 3.toShort()

    @Before
    fun setup() {
        every { bundle.getShort(any()) } returns expect
        every { bundle.getShort(any(), any()) } returns expectWithDefault
        every { intent.getShortExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putShort(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getShort(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getShort(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent, defaultValue)
        verify(exactly = 1) { intent.getShortExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}