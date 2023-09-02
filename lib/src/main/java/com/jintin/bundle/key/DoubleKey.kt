package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class DoubleKey(override val key: String) : DefaultTypedKey<Double> {
    override fun put(bundle: Bundle, value: Double) {
        bundle.putDouble(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Double): Double {
        return bundle.getDouble(key, defaultValue)
    }

    override fun get(bundle: Bundle): Double {
        return bundle.getDouble(key)
    }
}