package com.bussiness.komposecountrycodepicker.data

internal data class Transformation(
    val formatted: String?,
    val originalToTransformed: List<Int>,
    val transformedToOriginal: List<Int>,
)
