package com.eterocell.gradle.dsl

enum class YATDABuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}
