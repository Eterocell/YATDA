package com.eterocell.gradle.dsl

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.libs() = extensions.getByType<VersionCatalogsExtension>().named("libs")
