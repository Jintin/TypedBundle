package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class StringArrayListKey(override val key: String) :
    TypedBundle<ArrayList<String>>, TypedIntent<ArrayList<String>> {
    override fun put(bundle: Bundle, value: ArrayList<String>) {
        bundle.putStringArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<String>? {
        return bundle.getStringArrayList(key)
    }

    override fun put(intent: Intent, value: ArrayList<String>) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): ArrayList<String>? {
        return intent.getStringArrayListExtra(key)
    }
}