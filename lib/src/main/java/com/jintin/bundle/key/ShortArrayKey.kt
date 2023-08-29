package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class ShortArrayKey(override val key: String) : TypedKey<ShortArray> {
    override fun put(bundle: Bundle, value: ShortArray) {
        bundle.putShortArray(key, value)
    }

    override fun get(bundle: Bundle): ShortArray? {
        return bundle.getShortArray(key)
    }
}