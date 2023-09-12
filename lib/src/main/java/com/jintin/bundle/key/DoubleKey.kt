package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class DoubleKey(override val key: String) : DefaultTypedBundle<Double>,
    DefaultTypedIntent<Double> {
    override fun put(bundle: Bundle, value: Double) {
        bundle.putDouble(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Double): Double {
        return bundle.getDouble(key, defaultValue)
    }

    override fun get(bundle: Bundle): Double {
        return bundle.getDouble(key)
    }

    override fun put(intent: Intent, value: Double) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent, defaultValue: Double): Double {
        return intent.getDoubleExtra(key, defaultValue)
    }
}