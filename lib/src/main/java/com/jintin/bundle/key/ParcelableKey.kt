package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

@JvmInline
value class ParcelableKey<T : Parcelable>(override val key: String) :
    TypedBundle<T>, TypedIntent<T> {
    override fun put(bundle: Bundle, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle): T? {
        return bundle.getParcelable(key)
    }

    override fun put(intent: Intent, value: T) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): T? {
        return intent.getParcelableExtra(key)
    }
}