package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class CharSequenceKey(override val key: String) :
    TypedBundle<CharSequence>, TypedIntent<CharSequence> {
    override fun put(bundle: Bundle, value: CharSequence) {
        bundle.putCharSequence(key, value)
    }

    override fun get(bundle: Bundle): CharSequence? {
        return bundle.getCharSequence(key)
    }

    override fun put(intent: Intent, value: CharSequence) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): CharSequence? {
        return intent.getCharSequenceExtra(key)
    }
}