package com.jintin.bundle.key

import android.content.Intent

interface TypedIntent<T> {
    val key: String

    fun put(intent: Intent, value: T)

    fun get(intent: Intent): T?
}

interface DefaultTypedIntent<T>  {
    val key: String

    fun put(intent: Intent, value: T)

    fun get(intent: Intent, defaultValue: T): T
}