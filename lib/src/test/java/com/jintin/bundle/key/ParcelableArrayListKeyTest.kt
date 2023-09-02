package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableArrayListKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = ParcelableArrayListKey<FakeParcelable>("Test")
    private val expect = ArrayList<FakeParcelable>()

    @Before
    fun setup() {
        every { bundle.getParcelableArrayList<FakeParcelable>(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putParcelableArrayList(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getParcelableArrayList<FakeParcelable>(key.key) }
        assert(result == expect)
    }
}