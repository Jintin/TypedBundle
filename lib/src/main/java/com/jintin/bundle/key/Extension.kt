package com.jintin.bundle.key

import android.os.Bundle

fun <T> Bundle.put(key: TypedKey<T>, value: T) {
    key.put(this, value)
}

fun <T> Bundle.get(key: TypedKey<T>) = key.get(this)

fun <T> Bundle.get(key: DefaultTypedKey<T>, defaultValue: T) = key.get(this, defaultValue)
