package com.jintin.bundle.key

import android.os.Bundle
import android.os.IBinder

@JvmInline
value class BinderKey(override val key: String) : TypedKey<IBinder> {
    override fun put(bundle: Bundle, value: IBinder) {
        bundle.putBinder(key, value)
    }

    override fun get(bundle: Bundle): IBinder? {
        return bundle.getBinder(key)
    }
}