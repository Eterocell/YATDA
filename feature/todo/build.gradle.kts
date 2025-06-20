plugins {
    alias(libs.plugins.build.logic.android.library)
    alias(libs.plugins.build.logic.android.compose)
    alias(libs.plugins.build.logic.android.hilt)
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.ui)

    implementation(libs.hilt.android)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
}
