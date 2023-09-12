package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle
import java.io.Serializable

@JvmInline
value class SerializableKey<T : Serializable>(override val key: String) :
    TypedBundle<T>, TypedIntent<T> {
    override fun put(bundle: Bundle, value: T) {
        bundle.putSerializable(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(bundle: Bundle): T? {
        return bundle.getSerializable(key) as? T
    }

    override fun put(intent: Intent, value: T) {
        intent.putExtra(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(intent: Intent): T? {
        return intent.getSerializableExtra(key) as? T
    }
}