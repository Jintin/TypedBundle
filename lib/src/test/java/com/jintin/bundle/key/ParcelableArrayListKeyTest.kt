package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableArrayListKeyTest : BaseKeyTest() {
    private val key = ParcelableArrayListKey<FakeParcelable>("Test")
    private val expect = ArrayList<FakeParcelable>()

    @Before
    fun setup() {
        every { bundle.getParcelableArrayList<FakeParcelable>(any()) } returns expect
        every { intent.getParcelableArrayListExtra<FakeParcelable>(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putParcelableArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getParcelableArrayList<FakeParcelable>(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getParcelableArrayListExtra<FakeParcelable>(key.key) }
        assert(result == expect)
    }
}