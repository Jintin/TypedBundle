package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class IntegerArrayListKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = IntegerArrayListKey("Test")
    private val expect = ArrayList<Int>()

    @Before
    fun setup() {
        every { bundle.getIntegerArrayList(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putIntegerArrayList(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getIntegerArrayList(key.key) }
        assert(result == expect)
    }
}