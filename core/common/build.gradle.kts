plugins {
    alias(libs.plugins.build.logic.android.library)
    alias(libs.plugins.build.logic.android.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}
