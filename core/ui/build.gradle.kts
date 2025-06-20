plugins {
    alias(libs.plugins.build.logic.android.library)
    alias(libs.plugins.build.logic.android.compose)
}

dependencies {
    api(projects.core.design)
    api(projects.core.model)
}
