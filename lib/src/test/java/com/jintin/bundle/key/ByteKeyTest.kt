package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ByteKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = ByteKey("Test")
    private val expect = 1.toByte()
    private val expectWithDefault = 2.toByte()
    private val defaultValue = 3.toByte()

    @Before
    fun setup() {
        every { bundle.getByte(any()) } returns expect
        every { bundle.getByte(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putByte(key.key, expect) }
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
}