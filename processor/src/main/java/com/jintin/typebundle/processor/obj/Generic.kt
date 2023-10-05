package com.jintin.typebundle.processor.obj

import com.squareup.kotlinpoet.TypeVariableName

data class Generic(
    val name: TypeVariableName,
    val getApiVersion: Api? = null,
    val extraCast: Boolean = false,
    val inline: Boolean = true
)