package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ByteArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = ByteArrayKey("Test")
    private val expect = ByteArray(2) { 1 }

    @Before
    fun setup() {
        every { bundle.getByteArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putByteArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getByteArray(key.key) }
        assert(result.contentEquals(expect))
    }
}