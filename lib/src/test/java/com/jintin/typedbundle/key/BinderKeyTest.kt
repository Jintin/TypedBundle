package com.jintin.typedbundle.key

import android.os.Binder
import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class BinderKeyTest {

    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = BinderKey("Test")
    private val expect = mockk<Binder>()

    @Before
    fun setup() {
        every { bundle.getBinder(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putBinder(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getBinder(key.key) }
        assert(result == expect)
    }
}