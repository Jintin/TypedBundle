package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BooleanKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = BooleanKey("Test")
    private val expect = true
    private val expectWithDefault = false
    private val defaultValue = true

    @Before
    fun setup() {
        every { bundle.getBoolean(any()) } returns expect
        every { bundle.getBoolean(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putBoolean(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getBoolean(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getBoolean(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}