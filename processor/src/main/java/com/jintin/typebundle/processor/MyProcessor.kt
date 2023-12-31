package com.jintin.typebundle.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.jintin.typebundle.processor.obj.Api
import com.jintin.typebundle.processor.obj.Definition
import com.jintin.typebundle.processor.obj.Generic
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.writeTo

class MyProcessor(private val codeGenerator: CodeGenerator, private val logger: KSPLogger) :
    SymbolProcessor {

    private val data = listOf(
        Definition(
            name = "Binder", target = ClassName("android.os", "IBinder"), bundleUsageOnly = true
        ),
        Definition(ClassName("android.os", "Bundle")),
        Definition(
            BooleanArray::class,
            bundleFromBase = true
        ),
        Definition(ByteArray::class),
        Definition(CharArray::class),
        Definition(
            DoubleArray::class,
            bundleFromBase = true
        ),
        Definition(FloatArray::class),
        Definition(
            IntArray::class,
            bundleFromBase = true
        ),
        Definition(
            LongArray::class,
            bundleFromBase = true
        ),
        Definition(ShortArray::class),
        Definition(
            name = "SparseParcelableArray",
            target = ClassName("android.util", "SparseArray").parameterizedBy(PARCELABLE_CLASS),
            genericGet = Generic(name = PARCELABLE_CLASS, getApiVersion = Api.TIRAMISU),
            bundleUsageOnly = true,
        ),
        Definition(
            name = "ParcelableArray",
            target = Array::class.parameterizedBy(PARCELABLE_CLASS),
            genericGet = Generic(
                name = PARCELABLE_CLASS,
                getApiVersion = Api.TIRAMISU,
                extraCast = true
            )
        ),
        Definition(
            name = "ParcelableArrayList",
            target = ArrayList::class.parameterizedBy(PARCELABLE_CLASS),
            genericGet = Generic(name = PARCELABLE_CLASS, getApiVersion = Api.TIRAMISU),
            intentPutFullName = true
        ),
        Definition(
            name = "StringArrayList",
            target = ArrayList::class.parameterizedBy(
                String::class
            ),
            intentPutFullName = true
        ),
        Definition(
            name = "CharSequenceArray",
            target = Array::class.parameterizedBy(CharSequence::class)
        ),
        Definition(
            name = "CharSequenceArrayList",
            target = ArrayList::class.parameterizedBy(CharSequence::class),
            intentPutFullName = true
        ),
        Definition(
            name = "IntegerArrayList",
            target = ArrayList::class.parameterizedBy(Int::class),
            intentPutFullName = true
        ),
        Definition(
            kClass = Boolean::class,
            bundleGetWithDefault = true,
            bundleGetNonNull = true,
            bundleFromBase = true,
            intentGetWithDefault = true
        ),
        Definition(
            kClass = Byte::class,
            bundleGetNonNull = true,
            bundleGetWithDefault = true,
            intentGetWithDefault = true
        ),
        Definition(
            kClass = Char::class,
            bundleGetNonNull = true,
            bundleGetWithDefault = true,
            intentGetWithDefault = true
        ),
        Definition(
            CharSequence::class,
            bundleGetWithDefault = true
        ),
        Definition(
            kClass = Double::class,
            bundleGetNonNull = true,
            bundleGetWithDefault = true,
            bundleFromBase = true,
            intentGetWithDefault = true
        ),
        Definition(
            kClass = Float::class,
            bundleGetNonNull = true,
            bundleGetWithDefault = true,
            intentGetWithDefault = true
        ),
        Definition(
            kClass = Int::class,
            bundleGetNonNull = true,
            bundleGetWithDefault = true,
            bundleFromBase = true,
            intentGetWithDefault = true
        ),
        Definition(
            kClass = Long::class,
            bundleGetNonNull = true,
            bundleGetWithDefault = true,
            bundleFromBase = true,
            intentGetWithDefault = true
        ),
        Definition(
            kClass = Short::class,
            bundleGetNonNull = true,
            bundleGetWithDefault = true,
            intentGetWithDefault = true
        ),
        Definition(
            name = "Parcelable",
            target = PARCELABLE_CLASS,
            genericGet = Generic(name = PARCELABLE_CLASS, getApiVersion = Api.TIRAMISU),
        ),
        Definition(
            name = "Serializable",
            target = SERIALIZABLE_CLASS,
            genericGet = Generic(
                name = SERIALIZABLE_CLASS,
                getApiVersion = Api.TIRAMISU,
                extraCast = true
            ),
        ),
        Definition(className = ClassName("android.util", "SizeF"), bundleUsageOnly = true),
        Definition(className = ClassName("android.util", "Size"), bundleUsageOnly = true),
        Definition(
            kClass = String::class,
            bundleGetWithDefault = true,
            bundleFromBase = true
        ),
        Definition(
            name = "StringArray",
            target = Array::class.parameterizedBy(String::class),
            bundleFromBase = true
        ),
    )

    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation("com.jintin.bundle.TypedAnnotation")
            .forEach { _ ->
                data.forEach {
                    it.fileSpec().writeTo(
                        codeGenerator, Dependencies(true)
                    )
                }

            }
        return emptyList()
    }
}