plugins {
    id("build-logic.android.library")
    id("build-logic.android.hilt")
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(kotlin("test"))
}
