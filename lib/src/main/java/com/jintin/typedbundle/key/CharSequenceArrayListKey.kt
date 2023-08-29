package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class CharSequenceArrayListKey(override val key: String) :
    TypedKey<ArrayList<CharSequence>> {
    override fun put(bundle: Bundle, value: ArrayList<CharSequence>) {
        bundle.putCharSequenceArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<CharSequence>? {
        return bundle.getCharSequenceArrayList(key)
    }
}