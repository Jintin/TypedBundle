package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class CharSequenceArrayKey(override val key: String) : TypedKey<Array<CharSequence>> {
    override fun put(bundle: Bundle, value: Array<CharSequence>) {
        bundle.putCharSequenceArray(key, value)
    }

    override fun get(bundle: Bundle): Array<CharSequence>? {
        return bundle.getCharSequenceArray(key)
    }
}