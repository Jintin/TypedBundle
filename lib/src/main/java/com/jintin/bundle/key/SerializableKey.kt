package com.jintin.bundle.key

import android.os.Bundle
import java.io.Serializable


@JvmInline
value class SerializableKey<T: Serializable>(override val key: String) : TypedKey<T> {
    override fun put(bundle: Bundle, value: T) {
        bundle.putSerializable(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(bundle: Bundle): T? {
        return bundle.getSerializable(key) as? T
    }
}