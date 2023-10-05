package com.jintin.bundle.key

import android.os.Build
import com.jintin.bundle.ReflectionHelpers
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ParcelableArrayListKeyTest : BaseKeyTest() {
    private val key = ParcelableArrayListKey<FakeParcelable>("Test")
    private val expect = ArrayList<FakeParcelable>()

    @Before
    fun setup() {
        every { bundle.getParcelableArrayList<FakeParcelable>(any()) } returns expect
        every { intent.getParcelableArrayListExtra<FakeParcelable>(any()) } returns expect
        every { bundle.getParcelableArrayList(any(), FakeParcelable::class.java) } returns expect
        every {
            intent.getParcelableArrayListExtra(
                any(),
                FakeParcelable::class.java
            )
        } returns expect
    }

    @Test
    fun putTest() {
        bundle[key] = expect
        verify(exactly = 1) { bundle.putParcelableArrayList(key.key, expect) }
    }

    @Test
    fun putIntentTest() {
        intent.putExtra(key, expect)
        verify(exactly = 1) { intent.putParcelableArrayListExtra(key.key, expect) }
    }

    @Test
    fun getTestOldApi() {
        ReflectionHelpers.setStaticFieldViaReflection(
            Build.VERSION::class.java.getField("SDK_INT"),
            Build.VERSION_CODES.S_V2
        )
        val result = bundle[key]
        verify(exactly = 1) {
            bundle.getParcelableArrayList<FakeParcelable>(key.key)
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
            bundle.getParcelableArrayList(
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
            intent.getParcelableArrayListExtra<FakeParcelable>(key.key)
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
            intent.getParcelableArrayListExtra(
                key.key,
                FakeParcelable::class.java
            )
        }
        assert(result == expect)
    }
}