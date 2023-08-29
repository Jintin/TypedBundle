package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class ByteArrayKey(override val key: String) : TypedKey<ByteArray> {
    override fun put(bundle: Bundle, value: ByteArray) {
        bundle.putByteArray(key, value)
    }

    override fun get(bundle: Bundle): ByteArray? {
        return bundle.getByteArray(key)
    }
}