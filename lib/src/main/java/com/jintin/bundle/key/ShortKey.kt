package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle

@JvmInline
value class ShortKey(override val key: String) : DefaultTypedBundle<Short>,
    DefaultTypedIntent<Short> {
    override fun put(bundle: Bundle, value: Short) {
        bundle.putShort(key, value)
    }

    override fun get(bundle: Bundle, defaultValue: Short): Short {
        return bundle.getShort(key, defaultValue)
    }

    override fun get(bundle: Bundle): Short {
        return bundle.getShort(key)
    }

    override fun put(intent: Intent, value: Short) {
        intent.putExtra(key, value)
    }

    override fun get(intent: Intent, defaultValue: Short): Short {
        return intent.getShortExtra(key, defaultValue)
    }
}