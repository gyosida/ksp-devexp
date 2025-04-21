package com.yosida.talks.ksp.analytics_api.annotations

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class FeatureFlagDescriptor(
    val id: String = "",
    val description: String
)
