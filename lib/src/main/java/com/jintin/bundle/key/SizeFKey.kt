package com.jintin.bundle.key

import android.os.Bundle
import android.util.SizeF

@JvmInline
value class SizeFKey(override val key: String) : TypedKey<SizeF> {
    override fun put(bundle: Bundle, value: SizeF) {
        bundle.putSizeF(key, value)
    }

    override fun get(bundle: Bundle): SizeF? {
        return bundle.getSizeF(key)
    }
}