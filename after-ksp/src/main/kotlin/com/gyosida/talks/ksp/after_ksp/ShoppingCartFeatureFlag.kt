package com.gyosida.talks.ksp.after_ksp

import com.yosida.talks.ksp.analytics_api.FeatureFlag
import com.yosida.talks.ksp.analytics_api.annotations.GenFeatureFlag

@GenFeatureFlag
enum class ShoppingCartFeatureFlag(override val id: String): FeatureFlag {
    QuickCheckout("quick_checkout"),
    GridCartList("grid_cart_list");

    override fun getDescription(): String {
        return when (this) {
            QuickCheckout -> "Enables the quick checkout feature in the shopping cart."
            GridCartList -> "Enables the grid layout for the shopping cart list."
        }
    }
}
