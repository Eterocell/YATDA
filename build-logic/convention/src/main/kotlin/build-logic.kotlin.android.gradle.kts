import com.eterocell.gradle.dsl.kotlinCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.android")
}

kotlinCompile {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11

        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()
        freeCompilerArgs =
            listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlin.uuid.ExperimentalUuidApi",
            )
        optIn.addAll(
            "kotlin.RequiresOptIn",
            "kotlinx.coroutines.FlowPreview",
            "kotlinx.coroutines.ExperimentalCoroutinesApi",
            "kotlin.uuid.ExperimentalUuidApi",
            "kotlin.time.ExperimentalTime",
        )
    }
}
