package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class ByteArrayKey(override val key: String) :
    TypedBundle<ByteArray>, TypedIntent<ByteArray> {
    override fun put(bundle: Bundle, value: ByteArray) {
        bundle.putByteArray(key, value)
    }

    override fun get(bundle: Bundle): ByteArray? {
        return bundle.getByteArray(key)
    }

    override fun put(intent: Intent, value: ByteArray) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): ByteArray? {
        return intent.getByteArrayExtra(key)
    }
}