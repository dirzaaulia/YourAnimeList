import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.dirzaaulia.youranimelist.configureBadgingTasks
import com.dirzaaulia.youranimelist.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("nowinandroid.android.lint")
                apply("com.dropbox.dependency-guard")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configureBadgingTasks(extensions.getByType<BaseExtension>(), this)
            }
        }
    }

}