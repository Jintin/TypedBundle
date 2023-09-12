package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ByteKeyTest : BaseKeyTest() {

    private val key = ByteKey("Test")
    private val expect = 1.toByte()
    private val expectWithDefault = 2.toByte()
    private val defaultValue = 3.toByte()

    @Before
    fun setup() {
        every { bundle.getByte(any()) } returns expect
        every { bundle.getByte(any(), any()) } returns expectWithDefault
        every { intent.getByteExtra(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putByte(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getByte(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getByte(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent, defaultValue)
        verify(exactly = 1) { intent.getByteExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}