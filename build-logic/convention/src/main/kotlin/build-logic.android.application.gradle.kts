import com.eterocell.gradle.dsl.configureAndroidApplication
import com.eterocell.gradle.dsl.configureAppSigningConfigsForRelease

plugins {
    id("com.android.application")
    id("build-logic.android.base")
}

configureAndroidApplication {
    defaultConfig {
        applicationId = extra["aneon.project.group"].toString()
        targetSdk = 35

        versionCode = extra["aneon.project.version.code"].toString().toInt()
        versionName = extra["aneon.project.version.name"].toString()
    }
}

configureAppSigningConfigsForRelease()
