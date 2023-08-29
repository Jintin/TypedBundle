package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = ParcelableKey<FakeParcelable>("Test")
    private val expect = mockk<FakeParcelable>()

    @Before
    fun setup() {
        every { bundle.getParcelable<FakeParcelable>(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putParcelable(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getParcelable<FakeParcelable>(key.key) }
        assert(result == expect)
    }
}