package com.jintin.typebundle.processor

import com.squareup.kotlinpoet.ClassName
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

//    private fun FunSpec.Builder.suppress(): FunSpec.Builder {
//        return addAnnotation(
//            AnnotationSpec.builder(Suppress::class)
//                .addMember("%S", "UNCHECKED_CAST")
//                .build()
//        )
//    }