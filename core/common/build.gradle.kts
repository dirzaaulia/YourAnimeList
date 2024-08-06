plugins {
    alias(libs.plugins.youranimelist.android.library)
    alias(libs.plugins.youranimelist.android.hilt)
}

android {
    namespace = "com.dirzaaulia.youranimelist.core.common"
}

dependencies {
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}