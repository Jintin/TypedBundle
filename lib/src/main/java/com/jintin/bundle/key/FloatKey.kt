package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class FloatKey(override val key: String) : DefaultTypedBundle<Float>,
    DefaultTypedIntent<Float> {
    override fun put(bundle: Bundle, value: Float) {
        bundle.putFloat(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Float): Float {
        return bundle.getFloat(key, defaultValue)
    }

    override fun get(bundle: Bundle): Float {
        return bundle.getFloat(key)
    }

    override fun put(intent: Intent, value: Float) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent, defaultValue: Float): Float {
        return intent.getFloatExtra(key, defaultValue)
    }
}