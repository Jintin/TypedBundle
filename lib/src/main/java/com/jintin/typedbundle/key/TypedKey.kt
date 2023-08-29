package com.jintin.typedbundle.key

import android.os.Bundle

interface TypedKey<T> {
    val key: String

    fun put(bundle: Bundle, value: T)

    fun get(bundle: Bundle): T?
}

interface DefaultTypedKey<T> : TypedKey<T> {
    fun get(bundle: Bundle, defaultValue: T): T
}