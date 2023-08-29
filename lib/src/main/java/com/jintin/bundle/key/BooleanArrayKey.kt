package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class BooleanArrayKey(override val key: String) : TypedKey<BooleanArray> {
    override fun put(bundle: Bundle, value: BooleanArray) {
        bundle.putBooleanArray(key, value)
    }

    override fun get(bundle: Bundle): BooleanArray? {
        return bundle.getBooleanArray(key)
    }
}