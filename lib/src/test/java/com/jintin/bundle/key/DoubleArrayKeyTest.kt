package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class DoubleArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = DoubleArrayKey("Test")
    private val expect = DoubleArray(2) { 1.0 }

    @Before
    fun setup() {
        every { bundle.getDoubleArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putDoubleArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getDoubleArray(key.key) }
        assert(result.contentEquals(expect))
    }
}