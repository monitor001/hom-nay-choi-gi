package com.gaucon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.core.designsystem.GauConTheme
import com.gaucon.feature.activity.ActivityDetailScreen
import com.gaucon.feature.growth.GrowthScreen
import com.gaucon.feature.journal.JournalScreen
import com.gaucon.feature.library.LibraryScreen
import com.gaucon.feature.library.ResourceDetailScreen
import com.gaucon.feature.onboarding.ChildProfileScreen
import com.gaucon.feature.onboarding.OnboardingViewModel
import com.gaucon.feature.onboarding.WelcomeScreen
import com.gaucon.feature.reminder.ReminderSettingsScreen
import com.gaucon.feature.rewards.RewardsScreen
import com.gaucon.feature.today.TodayScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var prefs: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var fontScale by remember { mutableFloatStateOf(1.0f) }
            var onboardingDone by remember { mutableStateOf<Boolean?>(null) }
            val systemScale = LocalDensity.current.fontScale

            LaunchedEffect(systemScale) {
                val (resolved, done) = withContext(Dispatchers.IO) {
                    val stored = prefs.fontScale()
                    val scale = when {
                        stored != 1.0f -> stored
                        systemScale >= 1.3f -> {
                            prefs.setFontScale(1.15f)
                            1.15f
                        }
                        else -> 1.0f
                    }
                    scale to prefs.isOnboardingDone()
                }
                fontScale = resolved
                onboardingDone = done
            }

            GauConTheme(fontScale = fontScale) {
                if (onboardingDone == null) {
                    Text("Đang mở Hôm nay chơi gì?…")
                } else {
                    GauConNav(
                        startDestination = if (onboardingDone == true) Routes.Today else Routes.Welcome,
                        onOnboardingFinished = { onboardingDone = true },
                    )
                }
            }
        }
    }
}

object Routes {
    const val Welcome = "welcome"
    const val ChildProfile = "child_profile"
    const val Today = "today"
    const val Shop = "shop"
    const val Library = "library"
    const val Growth = "growth"
    const val Journal = "journal"
    const val More = "more"
    const val Activity = "activity/{activityId}"
    const val Resource = "resource/{resourceId}"
    fun activity(id: String) = "activity/$id"
    fun resource(id: String) = "resource/$id"
}

private data class BottomTab(
    val route: String,
    val label: String,
    val icon: ImageVector,
)

private val MainTabs = listOf(
    BottomTab(Routes.Today, "Hôm nay", Icons.Filled.Home),
    BottomTab(Routes.Shop, "Cửa hàng", Icons.Filled.Storefront),
    BottomTab(Routes.Library, "Tài liệu", Icons.AutoMirrored.Filled.MenuBook),
    BottomTab(Routes.Growth, "Phát triển", Icons.AutoMirrored.Filled.TrendingUp),
    BottomTab(Routes.More, "Nhắc", Icons.Filled.Notifications),
)

@Composable
fun GauConNav(
    startDestination: String,
    onOnboardingFinished: () -> Unit,
) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val route = backStack?.destination?.route
    val showBottomBar = MainTabs.any { it.route == route } || route == Routes.Journal

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    MainTabs.forEach { tab ->
                        val selected = route == tab.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigate(tab.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.label,
                                )
                            },
                            label = { Text(tab.label) },
                        )
                    }
                }
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(padding),
        ) {
            composable(Routes.Welcome) {
                WelcomeScreen(
                    onStart = { navController.navigate(Routes.ChildProfile) },
                )
            }
            composable(Routes.ChildProfile) {
                val vm: OnboardingViewModel = hiltViewModel()
                ChildProfileScreen(
                    onSaved = { name, birth ->
                        vm.saveChild(name, birth) {
                            onOnboardingFinished()
                            navController.navigate(Routes.Today) {
                                popUpTo(Routes.Welcome) { inclusive = true }
                            }
                        }
                    },
                )
            }
            composable(Routes.Today) {
                TodayScreen(
                    onOpenActivity = { id -> navController.navigate(Routes.activity(id)) },
                    onOpenShop = {
                        navController.navigate(Routes.Shop) {
                            launchSingleTop = true
                        }
                    },
                )
            }
            composable(Routes.Shop) {
                RewardsScreen()
            }
            composable(Routes.Library) {
                LibraryScreen(
                    onOpenResource = { id -> navController.navigate(Routes.resource(id)) },
                )
            }
            composable(Routes.Growth) {
                GrowthScreen()
            }
            composable(Routes.Journal) {
                JournalScreen()
            }
            composable(Routes.More) {
                ReminderSettingsScreen(
                    onOpenRewards = {
                        navController.navigate(Routes.Shop) { launchSingleTop = true }
                    },
                    onOpenJournal = {
                        navController.navigate(Routes.Journal)
                    },
                )
            }
            composable(
                route = Routes.Activity,
                arguments = listOf(navArgument("activityId") { type = NavType.StringType }),
            ) { entry ->
                val id = entry.arguments?.getString("activityId").orEmpty()
                ActivityDetailScreen(
                    activityId = id,
                    onBack = { navController.popBackStack() },
                    onCompleted = {
                        navController.popBackStack(Routes.Today, inclusive = false)
                    },
                    onOpenShop = {
                        navController.navigate(Routes.Shop) { launchSingleTop = true }
                    },
                    onOpenResource = { resId -> navController.navigate(Routes.resource(resId)) },
                )
            }
            composable(
                route = Routes.Resource,
                arguments = listOf(navArgument("resourceId") { type = NavType.StringType }),
            ) {
                ResourceDetailScreen(
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}
