package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class BundleKey(override val key: String) : TypedBundle<Bundle>,
    TypedIntent<Bundle> {
    override fun put(bundle: Bundle, value: Bundle) {
        bundle.putBundle(key, value)
    }

    override fun get(bundle: Bundle): Bundle? {
        return bundle.getBundle(key)
    }

    override fun put(intent: Intent, value: Bundle) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent): Bundle? {
        return intent.getBundleExtra(key)
    }

}