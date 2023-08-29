package com.jintin.bundle.key

import android.os.Bundle
import android.os.Parcelable

@JvmInline
value class ParcelableArrayListKey<T : Parcelable>(override val key: String) :
    TypedKey<ArrayList<T>> {
    override fun put(bundle: Bundle, value: ArrayList<T>) {
        bundle.putParcelableArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<T>? {
        return bundle.getParcelableArrayList(key)
    }
}