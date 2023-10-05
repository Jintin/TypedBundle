package com.jintin.typebundle.processor.obj

import com.jintin.typebundle.processor.BUILD_VERSION_CLASS
import com.jintin.typebundle.processor.BUNDLE_CLASS
import com.jintin.typebundle.processor.INTENT_CLASS
import com.jintin.typebundle.processor.PACKAGE_NAME
import com.jintin.typebundle.processor.VERSION_CODES_CLASS
import com.jintin.typebundle.processor.chainIfNotNull
import com.jintin.typebundle.processor.suppress
import com.jintin.typebundle.processor.tryParameterizedBy
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass

class Definition(
    private val name: String,
    private val target: TypeName,
    private val genericGet: Generic? = null,
    private val bundleGetNonNull: Boolean = false,
    private val bundleGetWithDefault: Boolean = false,
    private val bundleUsageOnly: Boolean = false,
    private val intentGetWithDefault: Boolean = false,
    private val intentPutFullName: Boolean = false,
) {

    constructor(
        className: ClassName,
        generic: Generic? = null,
        bundleGetNonNull: Boolean = false,
        bundleGetWithDefault: Boolean = false,
        bundleUsageOnly: Boolean = false,
        intentGetWithDefault: Boolean = false,
        intentPutFullName: Boolean = false,
    ) : this(
        name = className.simpleName,
        target = className,
        genericGet = generic,
        bundleGetNonNull = bundleGetNonNull,
        bundleGetWithDefault = bundleGetWithDefault,
        bundleUsageOnly = bundleUsageOnly,
        intentGetWithDefault = intentGetWithDefault,
        intentPutFullName = intentPutFullName
    )

    constructor(
        kClass: KClass<*>,
        generic: Generic? = null,
        bundleGetNonNull: Boolean = false,
        bundleGetWithDefault: Boolean = false,
        bundleUsageOnly: Boolean = false,
        intentGetWithDefault: Boolean = false,
        intentPutFullName: Boolean = false,
    ) : this(
        name = kClass.simpleName.orEmpty(),
        target = kClass.asClassName(),
        genericGet = generic,
        bundleGetNonNull = bundleGetNonNull,
        bundleGetWithDefault = bundleGetWithDefault,
        bundleUsageOnly = bundleUsageOnly,
        intentGetWithDefault = intentGetWithDefault,
        intentPutFullName = intentPutFullName
    )

    private val keyClass =
        ClassName(PACKAGE_NAME, name + "Key").tryParameterizedBy(genericGet?.name)

    fun fileSpec(): FileSpec {
        val builder = FileSpec.builder(PACKAGE_NAME, name + "Key")
            .addType(typeSpec())
            .addFunction(bundleSetSpec())
            .addFunction(bundleGetSpec(resultNonNull = bundleGetNonNull))
        if (bundleGetWithDefault) {
            builder.addFunction(bundleGetWithDefaultSpec())
        }
        if (!bundleUsageOnly) {
            builder.addFunction(intentSetSpec())
                .addFunction(intentGetSpec(withDefault = intentGetWithDefault))
        }
        return builder.build()
    }

    private fun typeSpec(): TypeSpec {
        val builder = TypeSpec
            .valueClassBuilder(name + "Key")
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter("key", String::class).build()
            )
            .addAnnotation(JvmInline::class)
            .addProperty(
                PropertySpec.builder("key", String::class).initializer("key").build()
            )
            .chainIfNotNull(genericGet?.name?.copy(reified = false)) {
                addTypeVariable(it)
            }
        return builder.build()
    }

    private fun bundleSetSpec(): FunSpec {
        val builder = FunSpec.builder("set")
            .addModifiers(KModifier.OPERATOR)
            .receiver(BUNDLE_CLASS)
            .addParameter("key", keyClass)
            .addParameter("value", target)
            .addCode("this.put$name(key.key, value)")
            .chainIfNotNull(genericGet) {
                if (it.name.isReified) {
                    addModifiers(KModifier.INLINE)
                }
                addTypeVariable(it.name)
            }
        return builder.build()
    }

    private fun bundleGetSpec(resultNonNull: Boolean): FunSpec {
        val builder = FunSpec.builder("get")
            .addModifiers(KModifier.OPERATOR)
            .receiver(BUNDLE_CLASS)
            .addParameter("key", keyClass)
            .chainIfNotNull(genericGet) {
                if (it.name.isReified) {
                    addModifiers(KModifier.INLINE)
                }
                addTypeVariable(it.name)
            }
        if (genericGet?.getApiVersion != null) {
            builder.beginControlFlow(
                "return if (%T.SDK_INT < %T.${genericGet.getApiVersion.name})",
                BUILD_VERSION_CLASS,
                VERSION_CODES_CLASS
            )
            if (genericGet.extraCast) {
                builder.addStatement("get$name(key.key) as? %T", target)
                    .suppress("DEPRECATION", "UNCHECKED_CAST")
            } else {
                builder.addStatement("get$name(key.key)")
                    .suppress("DEPRECATION")
            }
            builder
                .nextControlFlow("else")
                .addStatement("get$name(key.key, T::class.java)", target)
                .endControlFlow()
        } else {
            builder.addCode("return get$name(key.key)")
        }
        if (resultNonNull) {
            builder.returns(target)
        } else {
            builder.returns(target.copy(nullable = true))
        }
        return builder.build()
    }

    private fun bundleGetWithDefaultSpec(): FunSpec {
        val builder = FunSpec.builder("get")
            .receiver(BUNDLE_CLASS)
            .addParameter("key", keyClass)
            .addParameter("defaultValue", target)
            .returns(target)
            .chainIfNotNull(genericGet) { addTypeVariable(it.name) }
            .addCode("return get$name(key.key, defaultValue)")
        return builder.build()
    }

    private fun intentSetSpec(): FunSpec {
        val builder = FunSpec.builder("putExtra")
            .receiver(INTENT_CLASS)
            .addParameter("key", keyClass)
            .addParameter("value", target)
            .chainIfNotNull(genericGet) {
                if (it.name.isReified) {
                    addModifiers(KModifier.INLINE)
                }
                addTypeVariable(it.name)
            }
        if (intentPutFullName) {
            builder.addStatement("this.put${name}Extra(key.key, value)")
        } else {
            builder.addStatement("this.putExtra(key.key, value)")
        }
        return builder.build()
    }

    private fun intentGetSpec(withDefault: Boolean): FunSpec {
        val builder = FunSpec.builder("getExtra")
            .receiver(INTENT_CLASS)
            .addParameter("key", keyClass)
            .chainIfNotNull(genericGet) { addTypeVariable(it.name) }
        if (withDefault) {
            builder.addParameter("defaultValue", target)
            builder.addCode("return get${name}Extra(key.key, defaultValue)")
        } else if (genericGet?.getApiVersion != null) {
            builder.beginControlFlow(
                "return if (%T.SDK_INT < %T.${genericGet.getApiVersion.name})",
                BUILD_VERSION_CLASS,
                VERSION_CODES_CLASS
            ).addModifiers(KModifier.INLINE)

            if (genericGet.extraCast) {
                builder.addStatement("get${name}Extra(key.key) as? %T", target)
                    .suppress("DEPRECATION", "UNCHECKED_CAST")
            } else {
                builder.addStatement("get${name}Extra(key.key)")
                    .suppress("DEPRECATION")
            }
            builder
                .nextControlFlow("else")
                .addStatement("get${name}Extra(key.key, T::class.java)")
                .endControlFlow()
        } else {
            builder.addCode("return get${name}Extra(key.key)")
        }
        if (withDefault) {
            builder.returns(target)
        } else {
            builder.returns(target.copy(nullable = true))
        }
        return builder.build()
    }
}