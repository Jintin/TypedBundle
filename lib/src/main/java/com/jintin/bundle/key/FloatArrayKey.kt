package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class FloatArrayKey(override val key: String) :
    TypedBundle<FloatArray>, TypedIntent<FloatArray> {
    override fun put(bundle: Bundle, value: FloatArray) {
        bundle.putFloatArray(key, value)
    }

    override fun get(bundle: Bundle): FloatArray? {
        return bundle.getFloatArray(key)
    }

    override fun put(intent: Intent, value: FloatArray) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): FloatArray? {
        return intent.getFloatArrayExtra(key)
    }
}