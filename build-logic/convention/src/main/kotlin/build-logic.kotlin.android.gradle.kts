import com.eterocell.gradle.dsl.withAndroid
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

withAndroid {
    project.extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11

            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            optIn.addAll(
                "kotlin.RequiresOptIn",
                "kotlinx.coroutines.FlowPreview",
                "kotlinx.coroutines.ExperimentalCoroutinesApi",
                "kotlin.uuid.ExperimentalUuidApi",
                "kotlin.time.ExperimentalTime",
                "androidx.compose.material3.ExperimentalMaterial3Api",
            )
        }
    }
}
