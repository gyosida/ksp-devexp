package com.yosida.talks.ksp.after_ksp

import com.yosida.talks.ksp.analytics_api.FeatureFlag
import com.yosida.talks.ksp.analytics_api.annotations.FeatureFlagDescriptor
import com.yosida.talks.ksp.analytics_api.annotations.GenFeatureFlag

@GenFeatureFlag
enum class ShoppingCartFeatureFlag(override val id: String) : FeatureFlag {
    @FeatureFlagDescriptor(description = "Enables the quick checkout feature in the shopping cart.")
    QuickCheckout("quick_checkout"),

    @FeatureFlagDescriptor(
        id = "grid_cart_list_v2",
        description = "Enables the grid layout for the shopping cart list."
    )
    GridCartList("grid_cart_list");
}

@GenFeatureFlag
enum class OnboardingFeatureFlag(override val id: String) : FeatureFlag {
    @FeatureFlagDescriptor(description = "Enables the new onboarding experience.")
    NewOnboarding("new_onboarding"),

    @FeatureFlagDescriptor(description = "Enables the tutorial for the new onboarding experience.")
    NewOnboardingTutorial("new_onboarding_tutorial");
}
