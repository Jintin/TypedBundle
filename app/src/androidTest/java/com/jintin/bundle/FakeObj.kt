package com.jintin.bundle

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
class FakeParcelable(val value: Int) : Parcelable

class FakeSerializable(val value: Int) : Serializable