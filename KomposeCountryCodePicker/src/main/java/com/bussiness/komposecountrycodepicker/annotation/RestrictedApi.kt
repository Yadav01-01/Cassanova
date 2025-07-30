package com.bussiness.komposecountrycodepicker.annotation

@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This API is restricted for internal use only.",
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
public annotation class RestrictedApi
