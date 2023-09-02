package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class ShortKey(override val key: String) : DefaultTypedKey<Short> {
    override fun put(bundle: Bundle, value: Short) {
        bundle.putShort(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Short): Short {
        return bundle.getShort(key, defaultValue)
    }

    override fun get(bundle: Bundle): Short {
        return bundle.getShort(key)
    }
}