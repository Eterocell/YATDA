import com.eterocell.gradle.dsl.configureAndroidCommon

plugins {
    id("com.android.base")
    id("build-logic.kotlin.android")
}

configureAndroidCommon {
    namespace = androidNamespace
    compileSdk = 35
    buildToolsVersion = "35.0.0"
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val Project.androidNamespace
    get() =
        path
            .replace(":", ".")
            .let { if (it == ".app") "" else it.replace("-", ".") }
            .let { extra["aneon.project.group"] as String + it }
