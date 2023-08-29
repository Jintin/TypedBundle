package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class ByteKey(override val key: String) : DefaultTypedKey<Byte> {
    override fun put(bundle: Bundle, value: Byte) {
        bundle.putByte(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Byte): Byte {
        return bundle.getByte(key, defaultValue)
    }

    override fun get(bundle: Bundle): Byte {
        return bundle.getByte(key)
    }
}