package com.jintin.bundle.key

import android.os.Build
import android.util.SparseArray
import com.jintin.bundle.ReflectionHelpers
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SparseParcelableArrayKeyTest : BaseKeyTest() {
    private val key = SparseParcelableArrayKey<FakeParcelable>("Test")
    private val expect = SparseArray<FakeParcelable>()

    @Suppress("DEPRECATION")
    @Before
    fun setup() {
        every {
            bundle.getSparseParcelableArray<FakeParcelable>(any())
        } returns expect
        every {
            bundle.getSparseParcelableArray(any(), FakeParcelable::class.java)
        } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putSparseParcelableArray(key.key, expect) }
    }

    @Suppress("DEPRECATION")
    @Test
    fun getTestOldApi() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.S_V2
        )
        val result = bundle[key]
        verify(exactly = 1) {
            bundle.getSparseParcelableArray<FakeParcelable>(key.key)
        }
        assert(result == expect)
    }

    @Test
    fun getTest() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.TIRAMISU
        )
        val result = bundle[key]
        verify(exactly = 1) {
            bundle.getSparseParcelableArray(
                key.key,
                FakeParcelable::class.java
            )
        }
        assert(result == expect)
    }
}