package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class CharKey(override val key: String) : DefaultTypedBundle<Char>,
    DefaultTypedIntent<Char> {
    override fun put(bundle: Bundle, value: Char) {
        bundle.putChar(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Char): Char {
        return bundle.getChar(key, defaultValue)
    }

    override fun get(bundle: Bundle): Char {
        return bundle.getChar(key)
    }

    override fun put(intent: Intent, value: Char) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent, defaultValue: Char): Char {
        return intent.getCharExtra(key, defaultValue)
    }
}