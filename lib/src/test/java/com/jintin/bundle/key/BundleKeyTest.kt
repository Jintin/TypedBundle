package com.jintin.bundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BundleKeyTest : BaseKeyTest() {

    private val key = BundleKey("Test")
    private val expect = mockk<Bundle>()

    @Before
    fun setup() {
        every { bundle.getBundle(any()) } returns expect
        every { intent.getBundleExtra(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putBundle(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getBundle(key.key) }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getBundleExtra(key.key) }
        assert(result == expect)
    }
}