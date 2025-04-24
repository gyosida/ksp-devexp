package com.yosida.talks.ksp.after_ksp

import com.yosida.talks.ksp.analytics_api.FeatureFlag
import com.yosida.talks.ksp.analytics_api.annotations.FeatureFlagDescriptor
import com.yosida.talks.ksp.analytics_api.annotations.GenFeatureFlag

@GenFeatureFlag
enum class OnboardingFeatureFlag(override val id: String) : FeatureFlag {
    @FeatureFlagDescriptor(description = "Enables the new onboarding experience.")
    NewOnboarding("new_onboarding"),

    @FeatureFlagDescriptor(description = "Enables the tutorial for the new onboarding experience.")
    NewOnboardingTutorial("new_onboarding_tutorial");
}
