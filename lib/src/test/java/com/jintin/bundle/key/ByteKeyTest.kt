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
        bundle[key] = expect
        verify(exactly = 1) { bundle.putByte(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getByte(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = bundle.get(key, defaultValue)
        verify(exactly = 1) { bundle.getByte(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key, defaultValue)
        verify(exactly = 1) { intent.getByteExtra(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}