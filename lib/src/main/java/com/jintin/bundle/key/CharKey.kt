package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class CharKey(override val key: String) : DefaultTypedKey<Char> {
    override fun put(bundle: Bundle, value: Char) {
        bundle.putChar(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Char): Char {
        return bundle.getChar(key, defaultValue)
    }

    override fun get(bundle: Bundle): Char {
        return bundle.getChar(key)
    }
}