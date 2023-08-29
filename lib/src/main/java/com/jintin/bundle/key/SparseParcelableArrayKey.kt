package com.jintin.bundle.key

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray

@JvmInline
value class SparseParcelableArrayKey<T : Parcelable>(override val key: String) :
    TypedKey<SparseArray<T>> {
    override fun put(bundle: Bundle, value: SparseArray<T>) {
        bundle.putSparseParcelableArray(key, value)
    }

    override fun get(bundle: Bundle): SparseArray<T>? {
        return bundle.getSparseParcelableArray(key)
    }
}