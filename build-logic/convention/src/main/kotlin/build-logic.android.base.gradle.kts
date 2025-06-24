import com.eterocell.gradle.dsl.androidNamespace
import com.eterocell.gradle.dsl.configureAndroidCommon
import com.eterocell.gradle.dsl.libs

plugins {
    id("com.android.base")
    id("build-logic.kotlin.android")
}

configureAndroidCommon {
    namespace = androidNamespace
    compileSdk = 36
    buildToolsVersion = "36.0.0"
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    lint {
        xmlReport = true
        sarifReport = true
        checkDependencies = true
        disable += "GradleDependency"
    }
    testOptions.animationsDisabled = true
}

dependencies {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugar-jdk-libs").get())
}
