package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ByteArrayKeyTest : BaseKeyTest() {
    private val key = ByteArrayKey("Test")
    private val expect = ByteArray(2) { 1 }

    @Before
    fun setup() {
        every { bundle.getByteArray(any()) } returns expect
        every { intent.getByteArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putByteArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getByteArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getByteArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}