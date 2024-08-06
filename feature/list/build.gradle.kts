plugins {
    alias(libs.plugins.youranimelist.android.feature)
    alias(libs.plugins.youranimelist.android.library.compose)
    alias(libs.plugins.roborazzi)
    id("kotlinx-serialization")
}

android {
    namespace = "com.dirzaaulia.youranimelist.feature.list"
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.core.data)
    implementation(projects.core.domain)
}
