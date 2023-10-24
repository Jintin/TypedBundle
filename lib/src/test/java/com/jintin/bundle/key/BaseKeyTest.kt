package com.jintin.bundle.key

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import io.mockk.mockk

open class BaseKeyTest {
    protected val bundle = mockk<Bundle>(relaxed = true)
    protected val persistableBundle = mockk<PersistableBundle>(relaxed = true)
    protected val intent = mockk<Intent>(relaxed = true)
}