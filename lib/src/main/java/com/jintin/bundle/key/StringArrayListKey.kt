package com.jintin.bundle.key

import android.os.Bundle
import java.util.ArrayList

@JvmInline
value class StringArrayListKey(override val key: String) : TypedKey<ArrayList<String>> {
    override fun put(bundle: Bundle, value: ArrayList<String>) {
        bundle.putStringArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<String>? {
        return bundle.getStringArrayList(key)
    }
}