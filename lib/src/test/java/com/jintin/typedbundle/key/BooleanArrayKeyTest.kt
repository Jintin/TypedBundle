package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BooleanArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = BooleanArrayKey("Test")
    private val expect = BooleanArray(2)

    @Before
    fun setup() {
        every { bundle.getBooleanArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putBooleanArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getBooleanArray(key.key) }
        assert(result.contentEquals(expect))
    }
}