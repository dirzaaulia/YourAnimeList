plugins {
    alias(libs.plugins.youranimelist.android.library)
    alias(libs.plugins.youranimelist.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.dirzaaulia.youranimelist.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    api(projects.core.common)
    //TODO Comment first to disable, if needed uncomment
//    api(projects.core.database)
    api(projects.core.datastore)
    api(projects.core.network)

//    implementation(projects.core.analytics)
//    implementation(projects.core.notifications)
}
