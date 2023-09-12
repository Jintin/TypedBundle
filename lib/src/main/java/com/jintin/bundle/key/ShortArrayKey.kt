package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class ShortArrayKey(override val key: String) :
    TypedBundle<ShortArray>, TypedIntent<ShortArray> {
    override fun put(bundle: Bundle, value: ShortArray) {
        bundle.putShortArray(key, value)
    }

    override fun get(bundle: Bundle): ShortArray? {
        return bundle.getShortArray(key)
    }

    override fun put(intent: Intent, value: ShortArray) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): ShortArray? {
        return intent.getShortArrayExtra(key)
    }
}