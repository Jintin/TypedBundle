package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class IntegerArrayListKey(override val key: String) :
    TypedBundle<ArrayList<Int>>, TypedIntent<ArrayList<Int>> {
    override fun put(bundle: Bundle, value: ArrayList<Int>) {
        bundle.putIntegerArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<Int>? {
        return bundle.getIntegerArrayList(key)
    }

    override fun put(intent: Intent, value: ArrayList<Int>) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): ArrayList<Int>? {
        return intent.getIntegerArrayListExtra(key)
    }
}