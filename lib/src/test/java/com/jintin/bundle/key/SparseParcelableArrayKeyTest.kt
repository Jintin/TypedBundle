package com.jintin.bundle.key

import android.util.SparseArray
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SparseParcelableArrayKeyTest : BaseKeyTest() {
    private val key = SparseParcelableArrayKey<FakeParcelable>("Test")
    private val expect = SparseArray<FakeParcelable>()

    @Before
    fun setup() {
        every { bundle.getSparseParcelableArray<FakeParcelable>(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putSparseParcelableArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getSparseParcelableArray<FakeParcelable>(key.key) }
        assert(result == expect)
    }
}