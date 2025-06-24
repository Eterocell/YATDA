import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
    alias(libs.plugins.build.logic.android.test)
    alias(libs.plugins.androidx.baseline.profile)
}

android {

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "APP_BUILD_TYPE_SUFFIX", "\"\"")
        buildConfigField(
            "String",
            "APP_FLAVOR_SUFFIX",
            "\"\"",
        )
    }

    buildFeatures {
        buildConfig = true
    }

    // Use the same flavor dimensions as the application to allow generating Baseline Profiles on prod,
    // which is more close to what will be shipped to users (no fake data), but has ability to run the
    // benchmarks on demo, so we benchmark on stable data.
//    configureFlavors(this) { flavor ->
//        buildConfigField(
//            "String",
//            "APP_FLAVOR_SUFFIX",
//            "\"${flavor.applicationIdSuffix ?: ""}\""
//        )
//    }

    testOptions.managedDevices.allDevices {
        create<ManagedVirtualDevice>("pixel9Api35") {
            device = "Pixel 9"
            apiLevel = 35
            systemImageSource = "aosp"
        }
    }

    targetProjectPath = ":app"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

baselineProfile {
    // This specifies the managed devices to use that you run the tests on.
    managedDevices.clear()
    managedDevices += "pixel9Api35"

    // Don't use a connected device but rely on a GMD for consistency between local and CI builds.
    useConnectedDevices = false
}

dependencies {
    implementation(libs.androidx.benchmark.macro.junit4)
    implementation(libs.androidx.test.core.ktx)
    implementation(libs.androidx.test.espresso.core)
    implementation(libs.androidx.test.ext.junit.ktx)
    implementation(libs.androidx.test.rules)
    implementation(libs.androidx.test.runner)
    implementation(libs.androidx.test.uiautomator)
}
