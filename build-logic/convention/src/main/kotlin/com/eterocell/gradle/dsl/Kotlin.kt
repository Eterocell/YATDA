package com.eterocell.gradle.dsl

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

fun Project.withKotlin(block: Plugin<in Any>.() -> Unit) {
    plugins.withId("kotlin", block)
    withKotlinMultiplatform(block)
}

fun Project.withKotlinMultiplatform(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("kotlin-multiplatform", block)

fun Project.withKotlinDsl(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("org.gradle.kotlin.kotlin-dsl", block)

fun Project.withComposeCompiler(block: Plugin<in Any>.() -> Unit) =
    plugins.withId("org.jetbrains.kotlin.plugin.compose", block)

fun Project.configureComposeCompiler(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
    withComposeCompiler {
        extensions.configure(block)
    }
}
