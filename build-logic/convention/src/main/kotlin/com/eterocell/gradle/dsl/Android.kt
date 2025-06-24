package com.eterocell.gradle.dsl

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DynamicFeatureExtension
import com.android.build.api.dsl.TestExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.DynamicFeatureAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.api.variant.TestAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register

fun Project.withAndroidApplication(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("com.android.application", block)

fun Project.withAndroidLibrary(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("com.android.library", block)

fun Project.withAndroidDynamicFeature(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("com.android.dynamic-feature", block)

fun Project.withAndroidTest(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("com.android.test", block)

fun Project.withAndroid(block: Plugin<in Any>.() -> Unit) {
    withAndroidApplication(block)
    withAndroidLibrary(block)
    withAndroidDynamicFeature(block)
}

fun Project.configureAndroidCommon(block: CommonExtension<*, *, *, *, *, *>.() -> Unit) =
    withAndroid { configure("android", block) }

fun Project.configureAndroidApplication(block: ApplicationExtension.() -> Unit) =
    withAndroidApplication { extensions.configure(block) }

fun Project.configureAndroidLibrary(block: LibraryExtension.() -> Unit) =
    withAndroidLibrary { extensions.configure(block) }

fun Project.configureAndroidDynamicFeatures(block: DynamicFeatureExtension.() -> Unit) =
    withAndroidDynamicFeature { extensions.configure(block) }

fun Project.configureAndroidTest(block: TestExtension.() -> Unit) =
    withAndroidTest { extensions.configure(block) }

fun Project.configureApplicationAndroidComponents(block: ApplicationAndroidComponentsExtension.() -> Unit) {
    withAndroidApplication { extensions.configure(block) }
}

fun Project.configureLibraryAndroidComponents(block: LibraryAndroidComponentsExtension.() -> Unit) {
    withAndroidLibrary { extensions.configure(block) }
}

fun Project.configureDynamicFeatureAndroidComponents(block: DynamicFeatureAndroidComponentsExtension.() -> Unit) {
    withAndroidDynamicFeature { extensions.configure(block) }
}

fun Project.configureTestAndroidComponents(block: TestAndroidComponentsExtension.() -> Unit) {
    withAndroidTest { extensions.configure(block) }
}

fun Project.withBuildType(buildType: String, block: () -> Unit) {
    if (taskRequestContains(buildType)) block()
}

fun Project.withAndroidSourcesJar() {
    configure<BaseExtension> {
        tasks.register<Jar>("androidSourcesJar") {
            archiveClassifier.set("sources")
            from(
                sourceSets["main"].java.srcDirs,
                "src/main/kotlin",
            )
        }
    }
}

val Project.androidNamespace
    get() =
        path
            .replace(":", ".")
            .let { if (it == ".app") "" else it.replace("-", ".") }
            .let { extra["yatda.project.group"] as String + it }
