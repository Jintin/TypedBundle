package com.jintin.bundle

import android.content.Intent
import android.os.Bundle
import com.jintin.bundle.key.DefaultTypedBundle
import com.jintin.bundle.key.DefaultTypedIntent
import com.jintin.bundle.key.TypedBundle
import com.jintin.bundle.key.TypedIntent

fun <T> Bundle.put(key: TypedBundle<T>, value: T) {
    key.put(this, value)
}

fun <T> Bundle.get(key: TypedBundle<T>) = key.get(this)

fun <T> Bundle.get(key: DefaultTypedBundle<T>, defaultValue: T) = key.get(this, defaultValue)

fun <T> Intent.put(key: TypedIntent<T>, value: T) {
    key.put(this, value)
}
fun <T> Intent.put(key: DefaultTypedIntent<T>, value: T) {
    key.put(this, value)
}

fun <T> Intent.get(key: TypedIntent<T>) = key.get(this)

fun <T> Intent.get(key: DefaultTypedIntent<T>, defaultValue: T) = key.get(this, defaultValue)
