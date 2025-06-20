plugins {
    alias(libs.plugins.build.logic.android.library)
    alias(libs.plugins.build.logic.android.hilt)
}

dependencies {
    api(projects.core.database)
    api(projects.core.model)
}
