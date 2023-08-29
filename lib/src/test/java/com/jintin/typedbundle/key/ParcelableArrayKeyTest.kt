package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableArrayKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = ParcelableArrayKey<FakeParcelable>("Test")
    private val expect = Array<FakeParcelable>(size = 2) { mockk() }

    @Before
    fun setup() {
        every { bundle.getParcelableArray(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putParcelableArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getParcelableArray(key.key) }
        assert(result.contentEquals(expect))
    }
}