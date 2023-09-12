package com.jintin.bundle.key

import android.os.Bundle

interface TypedBundle<T> {
    val key: String

    fun put(bundle: Bundle, value: T)

    fun get(bundle: Bundle): T?
}

interface DefaultTypedBundle<T> : TypedBundle<T> {
    fun get(bundle: Bundle, defaultValue: T): T
}