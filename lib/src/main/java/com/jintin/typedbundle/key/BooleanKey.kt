package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class BooleanKey(override val key: String) : DefaultTypedKey<Boolean> {
    override fun put(bundle: Bundle, value: Boolean) {
        bundle.putBoolean(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Boolean): Boolean {
        return bundle.getBoolean(key, defaultValue)
    }

    override fun get(bundle: Bundle): Boolean {
        return bundle.getBoolean(key)
    }
}