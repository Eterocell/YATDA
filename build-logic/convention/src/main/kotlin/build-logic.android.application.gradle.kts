import com.eterocell.gradle.dsl.configureAndroidApplication
import com.eterocell.gradle.dsl.configureAppSigningConfigsForRelease

plugins {
    id("com.android.application")
    id("build-logic.android.base")
}

configureAndroidApplication {
    defaultConfig {
        applicationId = extra["yatda.project.group"].toString()
        targetSdk = 36

        versionCode = extra["yatda.project.version.code"].toString().toInt()
        versionName = extra["yatda.project.version.name"].toString()
    }
}

configureAppSigningConfigsForRelease()
