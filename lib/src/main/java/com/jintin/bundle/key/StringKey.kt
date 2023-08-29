package com.jintin.bundle.key

import android.os.Bundle

@JvmInline
value class StringKey(override val key: String) : DefaultTypedKey<String> {
    override fun put(bundle: Bundle, value: String) {
        bundle.putString(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: String): String = bundle.getString(key, defaultValue)


    override fun get(bundle: Bundle): String? = bundle.getString(key)

}