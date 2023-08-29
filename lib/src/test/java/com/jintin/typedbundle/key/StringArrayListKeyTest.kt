package com.jintin.typedbundle.key

import android.os.Bundle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class StringArrayListKeyTest {
    private val bundle = mockk<Bundle>(relaxed = true)
    private val key = StringArrayListKey("Test")
    private val expect = ArrayList<String>()

    @Before
    fun setup() {
        every { bundle.getStringArrayList(any()) } returns expect
    }

    @Test
    fun putTest() {
        key.put(bundle, expect)
        verify(exactly = 1) { bundle.putStringArrayList(key.key, expect) }
    }

    @Test
    fun getTest() {
        val result = key.get(bundle)
        verify(exactly = 1) { bundle.getStringArrayList(key.key) }
        assert(result == expect)
    }
}