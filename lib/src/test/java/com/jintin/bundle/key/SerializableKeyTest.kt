package com.jintin.bundle.key

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SerializableKeyTest : BaseKeyTest() {

    private val key = SerializableKey<FakeSerializable>("Test")
    private val expect = mockk<FakeSerializable>()

    @Before
    fun setup() {
        every { bundle.getSerializable(any(), FakeSerializable::class.java) } returns expect
        every { intent.getSerializableExtra(any(), FakeSerializable::class.java) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putSerializable(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getSerializable(key.key, FakeSerializable::class.java) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getSerializableExtra(key.key, FakeSerializable::class.java) }
        assert(result == expect)
    }
}