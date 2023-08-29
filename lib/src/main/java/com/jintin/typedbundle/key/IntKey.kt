package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class IntKey(override val key: String) : DefaultTypedKey<Int> {
    override fun put(bundle: Bundle, value: Int) = bundle.putInt(key, value)

    override fun get(bundle: Bundle, defaultValue: Int) = bundle.getInt(key, defaultValue)

    override fun get(bundle: Bundle) = bundle.getInt(key)

}