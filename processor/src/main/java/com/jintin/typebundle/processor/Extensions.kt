package com.jintin.typebundle.processor

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeVariableName
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass

const val PACKAGE_NAME = "com.jintin.bundle.key"
val BUNDLE_CLASS = ClassName("android.os", "Bundle")
val INTENT_CLASS = ClassName("android.content", "Intent")
val PARCELABLE_CLASS =
    TypeVariableName.invoke("T", ClassName("android.os", "Parcelable")).copy(reified = true)
val SERIALIZABLE_CLASS =
    TypeVariableName.invoke("T", ClassName("java.io", "Serializable")).copy(reified = true)

val BUILD_VERSION_CLASS = ClassName("android.os", "Build", "VERSION")
val VERSION_CODES_CLASS = ClassName("android.os", "Build", "VERSION_CODES")

inline fun <T, V> T.chainIfNotNull(obj: V?, lambda: T.(V) -> T): T {
    return if (obj != null) {
        lambda.invoke(this, obj)
    } else {
        this
    }
}

fun ClassName.tryParameterizedBy(typeArguments: TypeName?): TypeName {
    return if (typeArguments != null) {
        parameterizedBy(typeArguments)
    } else {
        this
    }
}

fun KClass<*>.parameterizedBy(
    typeArgument: TypeName,
): ParameterizedTypeName {
    return this.asClassName().parameterizedBy(typeArgument)
}

fun FunSpec.Builder.suppress(vararg values: String): FunSpec.Builder {
    val builder = AnnotationSpec.builder(Suppress::class)
    values.forEach {
        builder.addMember("%S", it)
    }
    return addAnnotation(builder.build())
}