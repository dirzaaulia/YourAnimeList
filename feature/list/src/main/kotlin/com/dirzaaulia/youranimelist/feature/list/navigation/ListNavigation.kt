package com.dirzaaulia.youranimelist.feature.list.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

fun NavGraphBuilder.listScreen() {
    composable<ListScreen> {  }
}


@Serializable
object ListScreen