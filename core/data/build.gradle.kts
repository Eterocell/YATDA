plugins {
    id("build-logic.android.library")
    id("build-logic.android.hilt")
}

dependencies {
    api(projects.core.database)
    api(projects.core.model)
}
