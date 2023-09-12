package com.jintin.bundle.key

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableArrayKeyTest : BaseKeyTest() {
    private val key = ParcelableArrayKey<FakeParcelable>("Test")
    private val expect = Array<FakeParcelable>(size = 2) { mockk() }

    @Before
    fun setup() {
        every { bundle.getParcelableArray(any()) } returns expect
        every { intent.getParcelableArrayExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putParcelableArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getParcelableArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getParcelableArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }
}