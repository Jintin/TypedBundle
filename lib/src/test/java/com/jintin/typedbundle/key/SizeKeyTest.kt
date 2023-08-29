package com.jintin.typedbundle.key

import android.os.Bundle
import android.util.Size
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SizeKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = SizeKey("Test")
    private val expect = mockk<Size>()

    @Before
    fun setup() {
        every { bundle.getSize(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putSize(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getSize(key.key) }
        assert(result == expect)
    }
}