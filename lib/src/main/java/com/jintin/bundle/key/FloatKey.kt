package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class FloatKey(override val key: String) : DefaultTypedKey<Float> {
    override fun put(bundle: Bundle, value: Float) {
        bundle.putFloat(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Float): Float {
        return bundle.getFloat(key, defaultValue)
    }

    override fun get(bundle: Bundle): Float {
        return bundle.getFloat(key)
    }
}