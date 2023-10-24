package com.jintin.bundle

import android.content.Intent
import android.os.Bundle
import android.util.Size
import android.util.SizeF
import android.util.SparseArray
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jintin.bundle.key.BooleanArrayKey
import com.jintin.bundle.key.BooleanKey
import com.jintin.bundle.key.ByteArrayKey
import com.jintin.bundle.key.ByteKey
import com.jintin.bundle.key.CharArrayKey
import com.jintin.bundle.key.CharKey
import com.jintin.bundle.key.CharSequenceArrayKey
import com.jintin.bundle.key.CharSequenceArrayListKey
import com.jintin.bundle.key.CharSequenceKey
import com.jintin.bundle.key.DoubleArrayKey
import com.jintin.bundle.key.DoubleKey
import com.jintin.bundle.key.FloatArrayKey
import com.jintin.bundle.key.FloatKey
import com.jintin.bundle.key.IntArrayKey
import com.jintin.bundle.key.IntKey
import com.jintin.bundle.key.IntegerArrayListKey
import com.jintin.bundle.key.LongArrayKey
import com.jintin.bundle.key.LongKey
import com.jintin.bundle.key.ParcelableArrayKey
import com.jintin.bundle.key.ParcelableArrayListKey
import com.jintin.bundle.key.ParcelableKey
import com.jintin.bundle.key.SerializableKey
import com.jintin.bundle.key.ShortArrayKey
import com.jintin.bundle.key.ShortKey
import com.jintin.bundle.key.SizeFKey
import com.jintin.bundle.key.SizeKey
import com.jintin.bundle.key.SparseParcelableArrayKey
import com.jintin.bundle.key.StringArrayKey
import com.jintin.bundle.key.StringArrayListKey
import com.jintin.bundle.key.StringKey
import com.jintin.bundle.key.get
import com.jintin.bundle.key.getExtra
import com.jintin.bundle.key.putExtra
import com.jintin.bundle.key.set
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BundleInstrumentedTest {

    private val booleanArrayKey = BooleanArrayKey("booleanArrayKey")
    private val booleanKey = BooleanKey("booleanKey")
    private val byteArrayKey = ByteArrayKey("byteArrayKey")
    private val byteKey = ByteKey("byteKey")
    private val charArrayKey = CharArrayKey("charArrayKey")
    private val charKey = CharKey("charKey")
    private val charSequenceArrayKey = CharSequenceArrayKey("charSequenceArrayKey")
    private val charSequenceArrayListKey = CharSequenceArrayListKey("charSequenceArrayListKey")
    private val charSequenceKey = CharSequenceKey("charSequenceKey")
    private val doubleArrayKey = DoubleArrayKey("doubleArrayKey")
    private val doubleKey = DoubleKey("doubleKey")
    private val floatArrayKey = FloatArrayKey("floatArrayKey")
    private val floatKey = FloatKey("floatKey")
    private val intArrayKey = IntArrayKey("intArrayKey")
    private val integerArrayListKey = IntegerArrayListKey("integerArrayListKey")
    private val intKey = IntKey("intKey")
    private val longArrayKey = LongArrayKey("longArrayKey")
    private val longKey = LongKey("longKey")
    private val parcelableArrayKey = ParcelableArrayKey<FakeParcelable>("parcelableArrayKey")
    private val parcelableArrayListKey =
        ParcelableArrayListKey<FakeParcelable>("parcelableArrayListKey")
    private val parcelableKey = ParcelableKey<FakeParcelable>("parcelableKey")
    private val serializableKey = SerializableKey<FakeSerializable>("serializableKey")
    private val shortArrayKey = ShortArrayKey("shortArrayKey")
    private val shortKey = ShortKey("shortKey")
    private val sizeFKey = SizeFKey("sizeFKey")
    private val sizeKey = SizeKey("sizeKey")
    private val sparseParcelableArrayKey =
        SparseParcelableArrayKey<FakeParcelable>("sparseParcelableArrayKey")
    private val stringArrayKey = StringArrayKey("stringArrayKey")
    private val stringArrayListKey = StringArrayListKey("stringArrayListKey")
    private val stringKey = StringKey("stringKey")

    @Test
    fun testBundle() {
        val bundle = Bundle()

        val booleanArray = BooleanArray(3) { it % 2 == 0 }
        bundle[booleanArrayKey] = booleanArray
        assert(bundle[booleanArrayKey].contentEquals(booleanArray))

        val booleanData = true
        bundle[booleanKey] = booleanData
        assert(bundle[booleanKey] == booleanData)

        val byteArray = ByteArray(3) { it.toByte() }
        bundle[byteArrayKey] = byteArray
        assert(bundle[byteArrayKey].contentEquals(byteArray))

        val byteData = 3.toByte()
        bundle[byteKey] = byteData
        assert(bundle[byteKey] == byteData)

        val charArray = CharArray(3) { it.toChar() }
        bundle[charArrayKey] = charArray
        assert(bundle[charArrayKey].contentEquals(charArray))

        val charData = 5.toChar()
        bundle[charKey] = charData
        assert(bundle[charKey] == charData)

        val charSequenceArray = Array<CharSequence>(3) { it.toString() }
        bundle[charSequenceArrayKey] = charSequenceArray
        assert(bundle[charSequenceArrayKey].contentEquals(charSequenceArray))

        val charSequenceArrayList = arrayListOf<CharSequence>("1", "2", "3")
        bundle[charSequenceArrayListKey] = charSequenceArrayList
        assert(bundle[charSequenceArrayListKey] == (charSequenceArrayList))

        val charSequenceData: CharSequence = "123"
        bundle[charSequenceKey] = charSequenceData
        assert(bundle[charSequenceKey] == charSequenceData)

        val doubleArray = DoubleArray(3) { it.toDouble() }
        bundle[doubleArrayKey] = doubleArray
        assert(bundle[doubleArrayKey].contentEquals(doubleArray))

        val doubleData = 3.14
        bundle[doubleKey] = doubleData
        assert(bundle[doubleKey] == doubleData)

        val floatArray = FloatArray(3) { it.toFloat() }
        bundle[floatArrayKey] = floatArray
        assert(bundle[floatArrayKey].contentEquals(floatArray))

        val floatData = 3.14f
        bundle[floatKey] = floatData
        assert(bundle[floatKey] == floatData)

        val intArray = IntArray(3) { it }
        bundle[intArrayKey] = intArray
        assert(bundle[intArrayKey].contentEquals(intArray))

        val integerArrayList = arrayListOf(3, 4, 5)
        bundle[integerArrayListKey] = integerArrayList
        assert(bundle[integerArrayListKey] == integerArrayList)

        val intData = 123
        bundle[intKey] = intData
        assert(bundle[intKey] == intData)

        val longArray = LongArray(3) { it.toLong() }
        bundle[longArrayKey] = longArray
        assert(bundle[longArrayKey].contentEquals(longArray))

        val longData = 123L
        bundle[longKey] = longData
        assert(bundle[longKey] == longData)

        val parcelableArray = Array(3) { FakeParcelable(it) }
        bundle[parcelableArrayKey] = parcelableArray
        assert(bundle[parcelableArrayKey].contentEquals(parcelableArray))

        val parcelableArrayList = arrayListOf(FakeParcelable(2))
        bundle[parcelableArrayListKey] = parcelableArrayList
        assert(bundle[parcelableArrayListKey] == parcelableArrayList)

        val parcelableData = FakeParcelable(3)
        bundle[parcelableKey] = parcelableData
        assert(bundle[parcelableKey] == parcelableData)

        val serializable = FakeSerializable(3)
        bundle[serializableKey] = serializable
        assert(bundle[serializableKey] == serializable)

        val shortArray = ShortArray(3) { it.toShort() }
        bundle[shortArrayKey] = shortArray
        assert(bundle[shortArrayKey].contentEquals(shortArray))

        val shortData = 3.toShort()
        bundle[shortKey] = shortData
        assert(bundle[shortKey] == shortData)

        val sizeFData = SizeF(1f, 2f)
        bundle[sizeFKey] = sizeFData
        assert(bundle[sizeFKey] == sizeFData)

        val sizeData = Size(1, 2)
        bundle[sizeKey] = sizeData
        assert(bundle[sizeKey] == sizeData)

        val sparseParcelableArray = SparseArray<FakeParcelable>()
        bundle[sparseParcelableArrayKey] = sparseParcelableArray
        assert(bundle[sparseParcelableArrayKey] == sparseParcelableArray)

        val stringArray = arrayOf("1", "2", "3")
        bundle[stringArrayKey] = stringArray
        assert(bundle[stringArrayKey].contentEquals(stringArray))

        val stringArrayList = arrayListOf("1", "2", "3")
        bundle[stringArrayListKey] = stringArrayList
        assert(bundle[stringArrayListKey] == stringArrayList)

        val string = "1234"
        bundle[stringKey] = string
        assert(bundle[stringKey] == string)

    }

    @Test
    fun testIntent() {
        val intent = Intent()

        val booleanArray = BooleanArray(3) { it % 2 == 0 }
        intent.putExtra(booleanArrayKey, booleanArray)
        assert(intent.getExtra(booleanArrayKey).contentEquals(booleanArray))

        val booleanData = true
        intent.putExtra(booleanKey, booleanData)
        assert(intent.getExtra(booleanKey, false) == booleanData)

        val byteArray = ByteArray(3) { it.toByte() }
        intent.putExtra(byteArrayKey, byteArray)
        assert(intent.getExtra(byteArrayKey).contentEquals(byteArray))

        val byteData = 3.toByte()
        intent.putExtra(byteKey, byteData)
        assert(intent.getExtra(byteKey, 0) == byteData)

        val charArray = CharArray(3) { it.toChar() }
        intent.putExtra(charArrayKey, charArray)
        assert(intent.getExtra(charArrayKey).contentEquals(charArray))

        val charData = 5.toChar()
        intent.putExtra(charKey, charData)
        assert(intent.getExtra(charKey, 'a') == charData)

        val charSequenceArray = Array<CharSequence>(3) { it.toString() }
        intent.putExtra(charSequenceArrayKey, charSequenceArray)
        assert(intent.getExtra(charSequenceArrayKey).contentEquals(charSequenceArray))

        val charSequenceArrayList = arrayListOf<CharSequence>("1", "2", "3")
        intent.putExtra(charSequenceArrayListKey, charSequenceArrayList)
        assert(intent.getExtra(charSequenceArrayListKey) == (charSequenceArrayList))

        val charSequenceData: CharSequence = "123"
        intent.putExtra(charSequenceKey, charSequenceData)
        assert(intent.getExtra(charSequenceKey) == charSequenceData)

        val doubleArray = DoubleArray(3) { it.toDouble() }
        intent.putExtra(doubleArrayKey, doubleArray)
        assert(intent.getExtra(doubleArrayKey).contentEquals(doubleArray))

        val doubleData = 3.14
        intent.putExtra(doubleKey, doubleData)
        assert(intent.getExtra(doubleKey, 0.0) == doubleData)

        val floatArray = FloatArray(3) { it.toFloat() }
        intent.putExtra(floatArrayKey, floatArray)
        assert(intent.getExtra(floatArrayKey).contentEquals(floatArray))

        val floatData = 3.14f
        intent.putExtra(floatKey, floatData)
        assert(intent.getExtra(floatKey, 0f) == floatData)

        val intArray = IntArray(3) { it }
        intent.putExtra(intArrayKey, intArray)
        assert(intent.getExtra(intArrayKey).contentEquals(intArray))

        val integerArrayList = arrayListOf(3, 4, 5)
        intent.putExtra(integerArrayListKey, integerArrayList)
        assert(intent.getExtra(integerArrayListKey) == integerArrayList)

        val intData = 123
        intent.putExtra(intKey, intData)
        assert(intent.getExtra(intKey, 0) == intData)

        val longArray = LongArray(3) { it.toLong() }
        intent.putExtra(longArrayKey, longArray)
        assert(intent.getExtra(longArrayKey).contentEquals(longArray))

        val longData = 123L
        intent.putExtra(longKey, longData)
        assert(intent.getExtra(longKey, 0) == longData)

        val parcelableArray = Array(3) { FakeParcelable(it) }
        intent.putExtra(parcelableArrayKey, parcelableArray)
        assert(intent.getExtra(parcelableArrayKey).contentEquals(parcelableArray))

        val parcelableArrayList = arrayListOf(FakeParcelable(2))
        intent.putExtra(parcelableArrayListKey, parcelableArrayList)
        assert(intent.getExtra(parcelableArrayListKey) == parcelableArrayList)

        val parcelableData = FakeParcelable(3)
        intent.putExtra(parcelableKey, parcelableData)
        assert(intent.getExtra(parcelableKey) == parcelableData)

        val serializable = FakeSerializable(3)
        intent.putExtra(serializableKey, serializable)
        assert(intent.getExtra(serializableKey) == serializable)

        val shortArray = ShortArray(3) { it.toShort() }
        intent.putExtra(shortArrayKey, shortArray)
        assert(intent.getExtra(shortArrayKey).contentEquals(shortArray))

        val shortData = 3.toShort()
        intent.putExtra(shortKey, shortData)
        assert(intent.getExtra(shortKey, 0) == shortData)

        val stringArray = arrayOf("1", "2", "3")
        intent.putExtra(stringArrayKey, stringArray)
        assert(intent.getExtra(stringArrayKey).contentEquals(stringArray))

        val stringArrayList = arrayListOf("1", "2", "3")
        intent.putExtra(stringArrayListKey, stringArrayList)
        assert(intent.getExtra(stringArrayListKey) == stringArrayList)

        val string = "1234"
        intent.putExtra(stringKey, string)
        assert(intent.getExtra(stringKey) == string)

    }
}