package com.jintin.bundle.key

import android.os.Build
import com.jintin.bundle.ReflectionHelpers
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableKeyTest : BaseKeyTest() {

    private val key = ParcelableKey<FakeParcelable>("Test")
    private val expect = mockk<FakeParcelable>()

    @Before
    fun setup() {
        every { bundle.getParcelable<FakeParcelable>(any()) } returns expect
        every { intent.getParcelableExtra<FakeParcelable>(any()) } returns expect
        every { bundle.getParcelable(any(), FakeParcelable::class.java) } returns expect
        every { intent.getParcelableExtra(any(), FakeParcelable::class.java) } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putParcelable(key.key, expect) }
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
        verify(exactly = 1) {
            bundle.getParcelable<FakeParcelable>(key.key)
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
            bundle.getParcelable(
                key.key,
                FakeParcelable::class.java
            )
        }
        assert(result == expect)
    }

    @Test
    fun getIntentTestOldApi() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.S_V2
        )
        val result = intent.getExtra(key)
        verify(exactly = 1) {
            intent.getParcelableExtra<FakeParcelable>(key.key)
        }
        assert(result == expect)
    }

    @Test
    fun getIntentTest() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.TIRAMISU
        )
        val result = intent.getExtra(key)
        verify(exactly = 1) {
            intent.getParcelableExtra(
                key.key,
                FakeParcelable::class.java
            )
        }
        assert(result == expect)
    }
}