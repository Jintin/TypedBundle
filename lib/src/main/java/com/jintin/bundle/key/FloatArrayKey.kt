package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class FloatArrayKey(override val key: String) : TypedKey<FloatArray> {
    override fun put(bundle: Bundle, value: FloatArray) {
        bundle.putFloatArray(key, value)
    }

    override fun get(bundle: Bundle): FloatArray? {
        return bundle.getFloatArray(key)
    }
}