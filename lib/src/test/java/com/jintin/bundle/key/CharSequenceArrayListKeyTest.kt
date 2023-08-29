package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharSequenceArrayListKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = CharSequenceArrayListKey("Test")
    private val expect = ArrayList<CharSequence>()

    @Before
    fun setup() {
        every { bundle.getCharSequenceArrayList(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putCharSequenceArrayList(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getCharSequenceArrayList(key.key) }
        assert(result == expect)
    }
}