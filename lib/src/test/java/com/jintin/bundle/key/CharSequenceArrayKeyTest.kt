package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharSequenceArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = CharSequenceArrayKey("Test")
    private val expect = Array<CharSequence>(2) { "AA" }

    @Before
    fun setup() {
        every { bundle.getCharSequenceArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putCharSequenceArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getCharSequenceArray(key.key) }
        assert(result.contentEquals(expect))
    }
}