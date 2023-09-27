package com.jintin.bundle.key

import android.util.Size
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SizeKeyTest : BaseKeyTest() {

    private val key = SizeKey("Test")
    private val expect = mockk<Size>()

    @Before
    fun setup() {
        every { bundle.getSize(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putSize(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getSize(key.key) }
        assert(result == expect)
    }
}