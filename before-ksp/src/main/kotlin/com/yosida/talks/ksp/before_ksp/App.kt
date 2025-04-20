package com.yosida.talks.ksp.before_ksp

import com.yosida.talks.ksp.analytics_api.FeatureFlag
import com.yosida.talks.ksp.analytics_api.FeatureFlagController

fun main() {
    val controller = object : FeatureFlagController {
        override fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean {
            return true
        }
    }
    IsGridCartListEnabled(controller)
    IsQuickCheckoutEnabled(controller)
}
