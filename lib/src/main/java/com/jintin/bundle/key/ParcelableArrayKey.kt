package com.jintin.bundle.key

import android.os.Bundle
import android.os.Parcelable

@JvmInline
value class ParcelableArrayKey<T : Parcelable>(override val key: String) : TypedKey<Array<T>> {
    override fun put(bundle: Bundle, value: Array<T>) {
        bundle.putParcelableArray(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(bundle: Bundle): Array<T>? {
        return bundle.getParcelableArray(key) as? Array<T>
    }
}