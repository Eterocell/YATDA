import com.eterocell.gradle.dsl.configureAndroidTest
import com.eterocell.gradle.dsl.configureGradleManagedDevices
import com.eterocell.gradle.dsl.libs

plugins {
    id("build-logic.kotlin.android")
    id("com.android.test")
}

configureAndroidTest {
    namespace = androidNamespace
    compileSdk = 36
    buildToolsVersion = "36.1.0"
    defaultConfig {
        targetSdk = 36
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    configureGradleManagedDevices(this)
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
