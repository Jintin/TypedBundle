package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class BooleanArrayKey(override val key: String) :
    TypedBundle<BooleanArray>, TypedIntent<BooleanArray> {
    override fun put(bundle: Bundle, value: BooleanArray) {
        bundle.putBooleanArray(key, value)
    }

    override fun get(bundle: Bundle): BooleanArray? {
        return bundle.getBooleanArray(key)
    }

    override fun put(intent: Intent, value: BooleanArray) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): BooleanArray? {
        return intent.getBooleanArrayExtra(key)
    }
}