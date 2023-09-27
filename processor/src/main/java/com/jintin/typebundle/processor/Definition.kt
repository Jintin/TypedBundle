package com.jintin.typebundle.processor

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass

class Definition(
    private val name: String,
    private val target: TypeName,
    private val generic: TypeVariableName? = null,
    private val bundleGetNonNull: Boolean = false,
    private val bundleGetWithDefault: Boolean = false,
    private val bundleUsageOnly: Boolean = false,
    private val intentGetWithDefault: Boolean = false,
) {

    constructor(
        className: ClassName,
        generic: TypeVariableName? = null,
        bundleGetNonNull: Boolean = false,
        bundleGetWithDefault: Boolean = false,
        bundleUsageOnly: Boolean = false,
        intentGetWithDefault: Boolean = false,
    ) : this(
        name = className.simpleName,
        target = className,
        generic = generic,
        bundleGetNonNull = bundleGetNonNull,
        bundleGetWithDefault = bundleGetWithDefault,
        bundleUsageOnly = bundleUsageOnly,
        intentGetWithDefault = intentGetWithDefault,
    )

    constructor(
        kClass: KClass<*>,
        generic: TypeVariableName? = null,
        bundleGetNonNull: Boolean = false,
        bundleGetWithDefault: Boolean = false,
        bundleUsageOnly: Boolean = false,
        intentGetWithDefault: Boolean = false,
    ) : this(
        name = kClass.simpleName.orEmpty(),
        target = kClass.asClassName(),
        generic = generic,
        bundleGetNonNull = bundleGetNonNull,
        bundleGetWithDefault = bundleGetWithDefault,
        bundleUsageOnly = bundleUsageOnly,
        intentGetWithDefault = intentGetWithDefault,
    )

    private val keyClass = ClassName(PACKAGE_NAME, name + "Key").tryParameterizedBy(generic)

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
            .chainIfNotNull(generic?.copy(reified = false)) {
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
            .chainIfNotNull(generic) {
                if (it.isReified) {
                    addModifiers(KModifier.INLINE)
                }
                addTypeVariable(it)
            }
        return builder.build()
    }

    private fun bundleGetSpec(resultNonNull: Boolean): FunSpec {
        val builder = FunSpec.builder("get")
            .addModifiers(KModifier.OPERATOR)
            .receiver(BUNDLE_CLASS)
            .addParameter("key", keyClass)
            .chainIfNotNull(generic) {
                if (it.isReified) {
                    addModifiers(KModifier.INLINE)
                }
                addTypeVariable(it)
            }
        if (generic?.isReified == true) {
            builder.addCode("return get$name(key.key, T::class.java)", target)
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
            .chainIfNotNull(generic) { addTypeVariable(it) }
            .addCode("return get$name(key.key, defaultValue)")
        return builder.build()
    }

    private fun intentSetSpec(): FunSpec {
        return FunSpec.builder("putExtra")
            .receiver(INTENT_CLASS)
            .addParameter("key", keyClass)
            .addParameter("value", target)
            .addCode("this.putExtra(key.key, value)")
            .chainIfNotNull(generic) {
                if (it.isReified) {
                    addModifiers(KModifier.INLINE)
                }
                addTypeVariable(it)
            }
            .build()
    }

    private fun intentGetSpec(withDefault: Boolean): FunSpec {
        val builder = FunSpec.builder("getExtra")
            .receiver(INTENT_CLASS)
            .addParameter("key", keyClass)
            .chainIfNotNull(generic) { addTypeVariable(it) }
        if (withDefault) {
            builder.addParameter("defaultValue", target)
            builder.addCode("return get${name}Extra(key.key, defaultValue)")
        } else if (generic?.isReified == true) {
            builder.addCode("return get${name}Extra(key.key, T::class.java)")
                .addModifiers(KModifier.INLINE)
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