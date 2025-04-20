package com.yosida.talks.ksp.before_ksp

import com.yosida.talks.ksp.analytics_api.FeatureFlagController

class IsGridCartListEnabled(private val featureFlagController: FeatureFlagController) {
    operator fun invoke(): Boolean {
        return featureFlagController.isFeatureEnabled(ShoppingCartFeatureFlag.GridCartList)
    }
}
