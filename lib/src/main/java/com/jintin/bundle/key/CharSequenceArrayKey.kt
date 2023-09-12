package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class CharSequenceArrayKey(override val key: String) :
    TypedBundle<Array<CharSequence>>, TypedIntent<Array<CharSequence>> {
    override fun put(bundle: Bundle, value: Array<CharSequence>) {
        bundle.putCharSequenceArray(key, value)
    }

    override fun get(bundle: Bundle): Array<CharSequence>? {
        return bundle.getCharSequenceArray(key)
    }

    override fun put(intent: Intent, value: Array<CharSequence>) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): Array<CharSequence>? {
        return intent.getCharSequenceArrayExtra(key)
    }
}