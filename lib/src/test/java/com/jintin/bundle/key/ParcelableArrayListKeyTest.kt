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
        every { bundle.getParcelableArrayList(any(), FakeParcelable::class.java) } returns expect
        every { intent.getParcelableArrayListExtra(any(), FakeParcelable::class.java) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putParcelableArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) {
            bundle.getParcelableArrayList(
                key.key,
                FakeParcelable::class.java
            )
        }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) {
            intent.getParcelableArrayListExtra(
                key.key,
                FakeParcelable::class.java
            )
        }
        assert(result == expect)
    }
}