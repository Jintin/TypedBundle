package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class BooleanKey(override val key: String) : DefaultTypedBundle<Boolean>,
    DefaultTypedIntent<Boolean> {
    override fun put(bundle: Bundle, value: Boolean) {
        bundle.putBoolean(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Boolean): Boolean {
        return bundle.getBoolean(key, defaultValue)
    }

    override fun get(bundle: Bundle): Boolean {
        return bundle.getBoolean(key)
    }

    override fun put(intent: Intent, value: Boolean) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent, defaultValue: Boolean): Boolean {
        return intent.getBooleanExtra(key, defaultValue)
    }

}