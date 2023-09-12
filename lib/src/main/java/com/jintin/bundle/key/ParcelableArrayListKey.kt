package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

@JvmInline
value class ParcelableArrayListKey<T : Parcelable>(override val key: String) :
    TypedBundle<ArrayList<T>>, TypedIntent<ArrayList<T>> {
    override fun put(bundle: Bundle, value: ArrayList<T>) {
        bundle.putParcelableArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<T>? {
        return bundle.getParcelableArrayList(key)
    }

    override fun put(intent: Intent, value: ArrayList<T>) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): ArrayList<T>? {
        return intent.getParcelableArrayListExtra(key)
    }
}