plugins {
    alias(libs.plugins.youranimelist.android.library)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.dirzaaulia.youranimelist.core.ui"
}

dependencies {
    api(libs.androidx.metrics)
//    api(projects.core.analytics)
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

}