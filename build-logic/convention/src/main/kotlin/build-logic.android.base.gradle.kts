import com.eterocell.gradle.dsl.configureAndroidCommon
import com.eterocell.gradle.dsl.libs

plugins {
    id("com.android.base")
    id("build-logic.kotlin.android")
}

configureAndroidCommon {
    namespace = project.androidNamespace
    compileSdk {
        version =
            release(36) {
                minorApiLevel = 1
            }
    }
    buildToolsVersion = "36.1.0"
    defaultConfig.apply {
        minSdk = 24
    }
    compileOptions.apply {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    lint.apply {
        xmlReport = true
        sarifReport = true
        checkDependencies = true
        disable += "GradleDependency"
    }
    testOptions.animationsDisabled = true
}

dependencies {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugar-jdk-libs").get())
}

val Project.androidNamespace: String
    get() {
        val group =
            findProperty("yatda.project.group") as? String
                ?: error("Property 'yatda.project.group' not found in gradle.properties")
        val suffix =
            path
                .replace(":", ".")
                .let { if (it == ".app") "" else it.replace("-", ".") }
        return group + suffix
    }
