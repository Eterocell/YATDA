plugins {
    id("build-logic.android.library")
    id("build-logic.android.compose")
    id("build-logic.android.hilt")
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.ui)

    implementation(libs.hilt.android)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
}
