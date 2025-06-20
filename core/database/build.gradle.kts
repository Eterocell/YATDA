plugins {
    alias(libs.plugins.build.logic.android.library)
    alias(libs.plugins.build.logic.android.room)
    alias(libs.plugins.build.logic.android.hilt)
}

dependencies {
    api(projects.core.model)

    api(libs.kotlinx.datetime)
}
