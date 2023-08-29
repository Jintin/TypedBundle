package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ShortKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = ShortKey("Test")
    private val expect = 1.toShort()
    private val expectWithDefault = 2.toShort()
    private val defaultValue = 3.toShort()

    @Before
    fun setup() {
        every { bundle.getShort(any()) } returns expect
        every { bundle.getShort(any(), any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putShort(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getShort(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = key.get(bundle, defaultValue)
        verify(exactly = 1) { bundle.getShort(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }
}