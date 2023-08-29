package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharSequenceKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = CharSequenceKey("Test")
    private val expect = "1234"

    @Before
    fun setup() {
        every { bundle.getCharSequence(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putCharSequence(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getCharSequence(key.key) }
        assert(result == expect)
    }
}