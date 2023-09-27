package com.jintin.bundle.key

import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class StringKeyTest : BaseKeyTest() {

    private val key = StringKey("Test")
    private val expect = "ABCD"
    private val expectWithDefault = "EFGH"
    private val defaultValue = "1234"

    @Before
    fun setup() {
        every { bundle.getString(any()) } returns expect
        every { bundle.getString(any(), any()) } returns expectWithDefault
        every { intent.getStringExtra(any()) } returns expectWithDefault
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putString(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getString(key.key) }
        assert(result == expect)
    }

    @Test
    fun getWithDefaultTest() {
        val result = bundle.get(key, defaultValue)
        verify(exactly = 1) { bundle.getString(key.key, defaultValue) }
        assert(result == expectWithDefault)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getStringExtra(key.key) }
        assert(result == expectWithDefault)
    }
}