import com.eterocell.gradle.dsl.androidNamespace
import com.eterocell.gradle.dsl.configureAndroidTest
import com.eterocell.gradle.dsl.configureGradleManagedDevices
import com.eterocell.gradle.dsl.libs

plugins {
    id("build-logic.kotlin.android")
    id("com.android.test")
}

configureAndroidTest {
    namespace = androidNamespace
    compileSdk = 36
    buildToolsVersion = "36.1.0"
    defaultConfig {
        targetSdk = 36
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    configureGradleManagedDevices(this)
}

dependencies {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugar-jdk-libs").get())
}
