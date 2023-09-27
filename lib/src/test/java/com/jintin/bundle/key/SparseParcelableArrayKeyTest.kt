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
        every {
            bundle.getSparseParcelableArray(
                any(),
                FakeParcelable::class.java
            )
        } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putSparseParcelableArray(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) {
            bundle.getSparseParcelableArray(
                key.key,
                FakeParcelable::class.java
            )
        }
        assert(result == expect)
    }
}