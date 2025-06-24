import com.eterocell.gradle.dsl.configureAndroidApplication
import com.eterocell.gradle.dsl.configureAppSigningConfigsForRelease
import com.eterocell.gradle.dsl.configureApplicationAndroidComponents
import com.eterocell.gradle.dsl.configurePrintApksTask

plugins {
    id("com.android.application")
    id("com.dropbox.dependency-guard")
    id("build-logic.android.base")
}

configureAndroidApplication {
    defaultConfig {
        applicationId = extra["yatda.project.group"].toString()
        targetSdk = 36

        versionCode = extra["yatda.project.version.code"].toString().toInt()
        versionName = extra["yatda.project.version.name"].toString()
    }
    testOptions.animationsDisabled = true

    configureAppSigningConfigsForRelease()
}

configureApplicationAndroidComponents {
    configurePrintApksTask(this)
}
