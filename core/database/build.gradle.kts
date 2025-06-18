plugins {
    id("build-logic.android.library")
    id("build-logic.android.room")
    id("build-logic.android.hilt")
}

dependencies {
    api(projects.core.model)

    api(libs.kotlinx.datetime)
}
