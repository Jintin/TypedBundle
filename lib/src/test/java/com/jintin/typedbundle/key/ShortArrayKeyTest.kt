package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ShortArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = ShortArrayKey("Test")
    private val expect = ShortArray(2) { 1 }

    @Before
    fun setup() {
        every { bundle.getShortArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putShortArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getShortArray(key.key) }
        assert(result.contentEquals(expect))
    }
}