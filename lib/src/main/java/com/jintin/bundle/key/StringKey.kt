package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class StringKey(override val key: String) : DefaultTypedBundle<String>,
    TypedIntent<String> {
    override fun put(bundle: Bundle, value: String) {
        bundle.putString(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: String): String =
        bundle.getString(key, defaultValue)

    override fun get(bundle: Bundle): String? = bundle.getString(key)

    override fun put(intent: Intent, value: String) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): String? {
        return intent.getStringExtra(key)
    }

}