plugins {
    `kotlin-dsl`
}

dependencies {
    // DSL
    compileOnly(embeddedKotlin("gradle-plugin"))
    implementation(libs.gradle.plugin.android)
    implementation(libs.gradle.plugin.ksp)
    implementation(libs.gradle.plugin.room)
    implementation(libs.gradle.plugin.hilt)
    implementation(libs.gradle.plugin.dependency.guard)

    // TODO: Switch back to kotlin 2.2.0 after gradle supports it
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.21")
    implementation(libs.gradle.plugin.spotless)
    implementation(libs.gradle.plugin.kotlin)
    implementation(libs.gradle.plugin.compose.compiler)
}
