package com.jintin.bundle.key

import android.os.Bundle
import android.os.Parcelable

@JvmInline
value class ParcelableKey<T : Parcelable>(override val key: String) : TypedKey<T> {
    override fun put(bundle: Bundle, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle): T? {
        return bundle.getParcelable(key)
    }
}