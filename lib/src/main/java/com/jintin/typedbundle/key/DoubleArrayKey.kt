package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class DoubleArrayKey(override val key: String) : TypedKey<DoubleArray> {
    override fun put(bundle: Bundle, value: DoubleArray) {
        bundle.putDoubleArray(key, value)
    }

    override fun get(bundle: Bundle): DoubleArray? {
        return bundle.getDoubleArray(key)
    }
}