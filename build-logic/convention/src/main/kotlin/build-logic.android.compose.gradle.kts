import com.eterocell.gradle.dsl.configureAndroidCommon
import com.eterocell.gradle.dsl.configureComposeCompiler
import com.eterocell.gradle.dsl.libs
import com.eterocell.gradle.dsl.withAndroid

plugins {
    id("org.jetbrains.kotlin.plugin.compose")
}

configureAndroidCommon {
    defaultConfig.vectorDrawables.apply { useSupportLibrary = true }
    buildFeatures.compose = true

    testOptions.unitTests.apply {
        isIncludeAndroidResources = true
    }
}

withAndroid {
    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add("implementation", platform(bom))
        add("testImplementation", platform(bom))
        add("androidTestImplementation", platform(bom))

        add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
    }
}

configureComposeCompiler {
    fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }

    fun Provider<*>.relativeToRootProject(dir: String) = map {
        isolated.rootProject.projectDirectory
            .dir("build")
            .dir(projectDir.toRelativeString(rootDir))
    }.map { it.dir(dir) }

    project.providers
        .gradleProperty("enableComposeCompilerMetrics")
        .onlyIfTrue()
        .relativeToRootProject("compose-metrics")
        .let(metricsDestination::set)

    project.providers
        .gradleProperty("enableComposeCompilerReports")
        .onlyIfTrue()
        .relativeToRootProject("compose-reports")
        .let(reportsDestination::set)

    stabilityConfigurationFiles
        .add(isolated.rootProject.projectDirectory.file("compose_compiler_config.conf"))
}
