package com.yosida.talks.ksp.before_ksp

import com.yosida.talks.ksp.analytics_api.FeatureFlag

enum class ShoppingCartFeatureFlag(override val id: String): FeatureFlag {
    QuickCheckout("quick_checkout"),
    GridCartList("grid_cart_list")
}
