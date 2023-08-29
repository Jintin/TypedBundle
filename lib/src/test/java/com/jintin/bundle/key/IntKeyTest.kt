package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class IntKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = IntKey("Test")
    private val expect = 1234
    private val expectWithDefault = 2345
    private val defaultValue = 3456

    @Before
    fun setup() {
        every { bundle.getInt(any()) } returns expect
        every { bundle.getInt(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putInt(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getInt(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getInt(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}