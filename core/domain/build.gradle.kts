plugins {
    alias(libs.plugins.youranimelist.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.dirzaaulia.youranimelist.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    implementation(libs.javax.inject)
}