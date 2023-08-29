package com.jintin.typedbundle.key

import android.os.Bundle

@JvmInline
value class BundleKey(override val key: String) : TypedKey<Bundle> {
    override fun put(bundle: Bundle, value: Bundle) {
        bundle.putBundle(key, value)
    }

    override fun get(bundle: Bundle): Bundle? {
        return bundle.getBundle(key)
    }
}