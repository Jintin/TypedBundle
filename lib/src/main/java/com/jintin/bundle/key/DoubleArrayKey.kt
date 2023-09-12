package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class DoubleArrayKey(override val key: String) :
    TypedBundle<DoubleArray>, TypedIntent<DoubleArray> {
    override fun put(bundle: Bundle, value: DoubleArray) {
        bundle.putDoubleArray(key, value)
    }

    override fun get(bundle: Bundle): DoubleArray? {
        return bundle.getDoubleArray(key)
    }

    override fun put(intent: Intent, value: DoubleArray) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): DoubleArray? {
        return intent.getDoubleArrayExtra(key)
    }
}