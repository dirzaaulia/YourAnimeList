plugins {
    alias(libs.plugins.youranimelist.android.library)
    alias(libs.plugins.youranimelist.android.hilt)
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.dirzaaulia.youranimelist.core.network"
    testOptions {
        unitTests {
            //TODO Set to true to enable testing
            isIncludeAndroidResources = false
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    api(libs.kotlinx.datetime)
    api(projects.core.common)
    api(projects.core.model)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.ktor)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

    testImplementation(libs.kotlinx.coroutines.test)
}