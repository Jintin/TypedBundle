package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class CharSequenceArrayListKey(override val key: String) :
    TypedBundle<ArrayList<CharSequence>>, TypedIntent<ArrayList<CharSequence>> {
    override fun put(bundle: Bundle, value: ArrayList<CharSequence>) {
        bundle.putCharSequenceArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<CharSequence>? {
        return bundle.getCharSequenceArrayList(key)
    }

    override fun put(intent: Intent, value: ArrayList<CharSequence>) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): ArrayList<CharSequence>? {
        return intent.getCharSequenceArrayListExtra(key)
    }
}