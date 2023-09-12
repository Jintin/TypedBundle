package com.jintin.bundle.key

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableKeyTest : BaseKeyTest() {

    private val key = ParcelableKey<FakeParcelable>("Test")
    private val expect = mockk<FakeParcelable>()

    @Before
    fun setup() {
        every { bundle.getParcelable<FakeParcelable>(any()) } returns expect
        every { intent.getParcelableExtra<FakeParcelable>(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putParcelable(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        key.put(intent, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getParcelable<FakeParcelable>(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = key.get(intent)
        verify(exactly = 1) { intent.getParcelableExtra<FakeParcelable>(key.key) }
        assert(result == expect)
    }
}