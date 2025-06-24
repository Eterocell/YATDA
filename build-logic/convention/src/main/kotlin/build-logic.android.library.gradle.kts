import com.eterocell.gradle.dsl.configureAndroidLibrary
import com.eterocell.gradle.dsl.configureFlavors
import com.eterocell.gradle.dsl.configureGradleManagedDevices
import com.eterocell.gradle.dsl.configureLibraryAndroidComponents
import com.eterocell.gradle.dsl.configurePrintApksTask
import com.eterocell.gradle.dsl.disableUnnecessaryAndroidTests
import com.eterocell.gradle.dsl.libs

plugins {
    id("com.android.library")
    id("build-logic.android.base")
}

configureAndroidLibrary {
    buildFeatures {
        buildConfig = false
    }
    configureFlavors(this)
    configureGradleManagedDevices(this)
    // The resource prefix is derived from the module name,
    // so resources inside ":core:module1" must be prefixed with "core_module1_"
    resourcePrefix =
        path
            .split("""\W""".toRegex())
            .drop(1)
            .distinct()
            .joinToString(separator = "_")
            .lowercase() + "_"
}

configureLibraryAndroidComponents {
    configurePrintApksTask(this)
    disableUnnecessaryAndroidTests(project)
}

dependencies {
    add("testImplementation", libs.findLibrary("kotlin-test").get())
    add("androidTestImplementation", libs.findLibrary("kotlin-test").get())

    add("implementation", libs.findLibrary("androidx-tracing-ktx").get())
}
