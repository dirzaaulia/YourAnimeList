package com.dirzaaulia.youranimelist.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.dirzaaulia.youranimelist.R
import com.dirzaaulia.youranimelist.core.designsystem.icon.YalIcons

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */

//TODO Refactor resources from app to per feature resources
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    LIST(
        selectedIcon = YalIcons.ListAlt,
        unselectedIcon = YalIcons.ListAlt,
        iconTextId = R.string.list,
        titleTextId = R.string.list,
    ),
    ABOUT(
        selectedIcon = YalIcons.Information,
        unselectedIcon = YalIcons.Information,
        iconTextId = R.string.about,
        titleTextId = R.string.about,
    ),
}
