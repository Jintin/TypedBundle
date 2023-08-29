package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SerializableKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = SerializableKey<FakeSerializable>("Test")
    private val expect = mockk<FakeSerializable>()

    @Before
    fun setup() {
        every { bundle.getSerializable(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putSerializable(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getSerializable(key.key) }
        assert(result == expect)
    }
}