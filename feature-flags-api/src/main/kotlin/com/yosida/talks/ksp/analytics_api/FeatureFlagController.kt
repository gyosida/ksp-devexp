package com.yosida.talks.ksp.analytics_api

interface FeatureFlagController {
    /**
     * Checks if a feature flag is enabled.
     *
     * @param featureFlag The feature flag to check.
     * @return True if the feature flag is enabled, false otherwise.
     */
    fun isFeatureEnabled(featureFlag: FeatureFlag): Boolean
}
