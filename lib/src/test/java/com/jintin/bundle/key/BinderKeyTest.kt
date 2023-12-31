package com.jintin.bundle.key

import android.os.Binder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BinderKeyTest : BaseKeyTest() {

    private val key = BinderKey("Test")
    private val expect = mockk<Binder>()

    @Before
    fun setup() {
        every { bundle.getBinder(any()) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putBinder(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = bundle[key]
        verify(exactly = 1) { bundle.getBinder(key.key) }
        assert(result == expect)
    }
}