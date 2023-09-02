package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CharKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = CharKey("Test")
    private val expect = 'a'
    private val expectWithDefault = 'b'
    private val defaultValue = 'c'

    @Before
    fun setup() {
        every { bundle.getChar(any()) } returns expect
        every { bundle.getChar(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putChar(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getChar(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getChar(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}