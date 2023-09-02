package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = CharArrayKey("Test")
    private val expect = CharArray(2)

    @Before
    fun setup() {
        every { bundle.getCharArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putCharArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getCharArray(key.key) }
        assert(result.contentEquals(expect))
    }
}