package com.jintin.bundle.key

import android.os.Bundle
import android.util.Size

@JvmInline
value class SizeKey(override val key: String) :
    TypedBundle<Size> {
    override fun put(bundle: Bundle, value: Size) {
        bundle.putSize(key, value)
    }

    override fun get(bundle: Bundle): Size? {
        return bundle.getSize(key)
    }
}