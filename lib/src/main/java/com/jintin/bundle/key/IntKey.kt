package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class IntKey(override val key: String) : DefaultTypedBundle<Int>, DefaultTypedIntent<Int> {
    override fun put(bundle: Bundle, value: Int) = bundle.putInt(key, value)

    override fun get(bundle: Bundle, defaultValue: Int) = bundle.getInt(key, defaultValue)

    override fun get(bundle: Bundle) = bundle.getInt(key)

    override fun put(intent: Intent, value: Int) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent, defaultValue: Int): Int {
        return intent.getIntExtra(key, defaultValue)
    }

}