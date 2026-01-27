import com.eterocell.gradle.dsl.configureAndroidApplication
import com.eterocell.gradle.dsl.configureAppSigningConfigsForRelease
import com.eterocell.gradle.dsl.configureApplicationAndroidComponents
import com.eterocell.gradle.dsl.configureGradleManagedDevices
import com.eterocell.gradle.dsl.configurePrintApksTask

plugins {
    id("com.android.application")
    id("com.dropbox.dependency-guard")
    id("build-logic.android.base")
}

configureAndroidApplication {
    defaultConfig {
        applicationId =
            findProperty("yatda.project.group")?.toString()
                ?: error("Property 'yatda.project.group' not found in gradle.properties")
        targetSdk = 36

        versionCode =
            findProperty("yatda.project.version.code")?.toString()?.toInt()
                ?: error("Property 'yatda.project.version.code' not found in gradle.properties")
        versionName =
            findProperty("yatda.project.version.name")?.toString()
                ?: error("Property 'yatda.project.version.name' not found in gradle.properties")
    }

    configureAppSigningConfigsForRelease()
    configureGradleManagedDevices(this)
}

configureApplicationAndroidComponents {
    configurePrintApksTask(this)
}
