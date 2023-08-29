package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class IntegerArrayListKey(override val key: String) : TypedKey<ArrayList<Int>> {
    override fun put(bundle: Bundle, value: ArrayList<Int>) {
        bundle.putIntegerArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<Int>? {
        return bundle.getIntegerArrayList(key)
    }
}