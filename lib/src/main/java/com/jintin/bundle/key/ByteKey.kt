package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class ByteKey(override val key: String) : DefaultTypedBundle<Byte>,
    DefaultTypedIntent<Byte> {
    override fun put(bundle: Bundle, value: Byte) {
        bundle.putByte(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Byte): Byte {
        return bundle.getByte(key, defaultValue)
    }

    override fun get(bundle: Bundle): Byte {
        return bundle.getByte(key)
    }

    override fun put(intent: Intent, value: Byte) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent, defaultValue: Byte): Byte {
        return intent.getByteExtra(key, defaultValue)
    }
}