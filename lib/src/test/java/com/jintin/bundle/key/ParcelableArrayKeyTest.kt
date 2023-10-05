package com.jintin.bundle.key

import android.os.Build
import com.jintin.bundle.ReflectionHelpers
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableArrayKeyTest : BaseKeyTest() {
    private val key = ParcelableArrayKey<FakeParcelable>("Test")
    private val expect = Array<FakeParcelable>(size = 2) { mockk() }

    @Before
    fun setup() {
        every { bundle.getParcelableArray(any()) } returns expect
        every { intent.getParcelableArrayExtra(any()) } returns expect
        every { bundle.getParcelableArray(any(), FakeParcelable::class.java) } returns expect
        every { intent.getParcelableArrayExtra(any(), FakeParcelable::class.java) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putParcelableArray(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putExtra(key.key, expect) }
    }

    @Test
    fun getTestOldApi() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.S_V2
        )
        val result = bundle[key]
        verify(exactly = 1) { bundle.getParcelableArray(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getTest() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.TIRAMISU
        )
        val result = bundle[key]
        verify(exactly = 1) { bundle.getParcelableArray(key.key, FakeParcelable::class.java) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTestOldApi() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.S_V2
        )
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getParcelableArrayExtra(key.key) }
        assert(result.contentEquals(expect))
    }

    @Test
    fun getIntentTest() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.TIRAMISU
        )
        val result = intent.getExtra(key)
        verify(exactly = 1) { intent.getParcelableArrayExtra(key.key, FakeParcelable::class.java) }
        assert(result.contentEquals(expect))
    }
}