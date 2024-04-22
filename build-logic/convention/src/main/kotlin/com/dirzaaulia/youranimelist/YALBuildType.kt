package com.dirzaaulia.youranimelist

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class YALBuildTypeBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}