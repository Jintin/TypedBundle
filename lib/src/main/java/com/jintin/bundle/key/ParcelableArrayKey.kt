package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

@JvmInline
value class ParcelableArrayKey<T : Parcelable>(override val key: String) :
    TypedBundle<Array<T>>, TypedIntent<Array<T>> {
    override fun put(bundle: Bundle, value: Array<T>) {
        bundle.putParcelableArray(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(bundle: Bundle): Array<T>? {
        return bundle.getParcelableArray(key) as? Array<T>
    }

    override fun put(intent: Intent, value: Array<T>) {
        intent.putExtra(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun get(intent: Intent): Array<T>? {
        return intent.getParcelableArrayExtra(key) as? Array<T>
    }
}