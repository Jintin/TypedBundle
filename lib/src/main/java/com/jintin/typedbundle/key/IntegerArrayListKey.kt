package com.jintin.typedbundle.key

import android.os.Bundle
import java.util.ArrayList

@JvmInline
value class IntegerArrayListKey(override val key: String) : TypedKey<ArrayList<Int>> {
    override fun put(bundle: Bundle, value: ArrayList<Int>) {
        bundle.putIntegerArrayList(key, value)
    }

    override fun get(bundle: Bundle): ArrayList<Int>? {
        return bundle.getIntegerArrayList(key)
    }
}