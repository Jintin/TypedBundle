package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class CharSequenceKey(override val key: String) : TypedKey<CharSequence> {
    override fun put(bundle: Bundle, value: CharSequence) {
        bundle.putCharSequence(key, value)
    }

    override fun get(bundle: Bundle): CharSequence? {
        return bundle.getCharSequence(key)
    }
}