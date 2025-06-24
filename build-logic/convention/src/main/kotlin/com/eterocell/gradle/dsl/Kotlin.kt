package com.eterocell.gradle.dsl

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

fun Project.withKotlin(block: Plugin<in Any>.() -> Unit) {
    plugins.withId("kotlin", block)
    withKotlinAndroid(block)
    withKotlinMultiplatform(block)
}

fun Project.withKotlinAndroid(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("kotlin-android", block)

fun Project.withKotlinMultiplatform(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("kotlin-multiplatform", block)

fun Project.withKotlinDsl(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("org.gradle.kotlin.kotlin-dsl", block)

fun Project.withComposeCompiler(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("org.jetbrains.kotlin.plugin.compose", block)

fun Project.kotlinCompile(block: KotlinCompile.() -> Unit) = withKotlin { tasks.withType(block) }

fun Project.kotlinJvmProject(block: KotlinJvmProjectExtension.() -> Unit) =
    plugins.withId("kotlin") { configureKotlinJvmProject(block) }

fun Project.kotlinDslProject(block: KotlinJvmProjectExtension.() -> Unit) =
    withKotlinDsl { configureKotlinJvmProject(block) }

fun Project.configureComposeCompiler(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
    withComposeCompiler {
        extensions.configure(block)
    }
}

fun ExtensionAware.configureKotlinJvmProject(block: KotlinJvmProjectExtension.() -> Unit) =
    configure("kotlin", block)
