package com.jintin.bundle

import android.os.Bundle
import com.jintin.bundle.key.DefaultTypedBundle
import com.jintin.bundle.key.TypedBundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class ExtensionTest {

    @Test
    fun putTest() {
        val bundle = Bundle()
        val key = mockk<TypedBundle<String>>(relaxed = true)
        every { key.put(any(), any()) } returns Unit
        val value = "1234"
        bundle.put(key, value)
        verify(exactly = 1) { key.put(bundle, value) }
    }

    @Test
    fun getTest() {
        val bundle = Bundle()
        val key = mockk<TypedBundle<String>>(relaxed = true)
        val value = "1234"
        every { key.get(any()) } returns value
        val result = bundle.get(key)
        verify(exactly = 1) { key.get(bundle) }
        assert(result == value)
    }

    @Test
    fun getTestWithDefault() {
        val bundle = Bundle()
        val key = mockk<DefaultTypedBundle<String>>(relaxed = true)
        val value = "1234"
        every { key.get(any(), any()) } returns value
        val result = bundle.get(key, "2345")
        verify(exactly = 1) { key.get(bundle, "2345") }
        assert(result == value)
    }
}