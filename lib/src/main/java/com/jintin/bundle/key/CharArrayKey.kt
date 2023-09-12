package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class CharArrayKey(override val key: String) :
    TypedBundle<CharArray>, TypedIntent<CharArray> {
    override fun put(bundle: Bundle, value: CharArray) {
        bundle.putCharArray(key, value)
    }

    override fun get(bundle: Bundle): CharArray? {
        return bundle.getCharArray(key)
    }

    override fun put(intent: Intent, value: CharArray) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): CharArray? {
       return intent.getCharArrayExtra(key)
    }
}