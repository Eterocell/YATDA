package com.eterocell.gradle.dsl

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.kotlin.dsl.invoke

@Suppress("EnumEntryName")
enum class FlavorDimension {
    distributionType
}

@Suppress("EnumEntryName")
enum class YATDAFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    foss(FlavorDimension.distributionType, ".foss"),
    play(FlavorDimension.distributionType)
}

fun configureFlavors(
    commonExtension: CommonExtension,
    flavorConfigurationBlock: ProductFlavor.(flavor: YATDAFlavor) -> Unit = {},
) {
    commonExtension.apply {
        FlavorDimension.entries.forEach { flavorDimension ->
            flavorDimensions += flavorDimension.name
        }

        productFlavors {
            YATDAFlavor.entries.forEach { flavor ->
                register(flavor.name) {
                    dimension = flavor.dimension.name
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (flavor.applicationIdSuffix != null) {
                            applicationIdSuffix = flavor.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
