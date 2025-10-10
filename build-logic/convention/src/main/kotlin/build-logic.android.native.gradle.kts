import com.eterocell.gradle.dsl.configureAndroidCommon

configureAndroidCommon {
    ndkVersion = "29.0.14206865"

    defaultConfig.externalNativeBuild {
        cmake {
            arguments +=
                listOf(
                    "-DANDROID_STL=c++_shared",
                    "-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON", // Support 16KB memory page size
                )
            cFlags += "-std=c23"
            cppFlags += "-std=c++23"
            abiFilters("arm64-v8a", "armeabi-v7a", "x86", "x86_64")
        }
    }
    externalNativeBuild {
        cmake {
            version = "4.1.1"
        }
    }
}
