package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class CharArrayKey(override val key: String) : TypedKey<CharArray> {
    override fun put(bundle: Bundle, value: CharArray) {
        bundle.putCharArray(key, value)
    }

    override fun get(bundle: Bundle): CharArray? {
        return bundle.getCharArray(key)
    }
}