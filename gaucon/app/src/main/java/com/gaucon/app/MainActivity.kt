package com.gaucon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gaucon.core.datastore.UserPreferences
import com.gaucon.core.designsystem.GauConTheme
import com.gaucon.feature.activity.ActivityDetailScreen
import com.gaucon.feature.onboarding.ChildProfileScreen
import com.gaucon.feature.onboarding.OnboardingViewModel
import com.gaucon.feature.onboarding.WelcomeScreen
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
                withContext(Dispatchers.IO) {
                    val stored = prefs.fontScale()
                    val resolved = when {
                        stored != 1.0f -> stored
                        systemScale >= 1.3f -> {
                            prefs.setFontScale(1.15f)
                            1.15f
                        }
                        else -> 1.0f
                    }
                    fontScale = resolved
                    onboardingDone = prefs.isOnboardingDone()
                }
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
    const val Activity = "activity/{activityId}"
    fun activity(id: String) = "activity/$id"
}

@Composable
fun GauConNav(
    startDestination: String,
    onOnboardingFinished: () -> Unit,
) {
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val route = backStack?.destination?.route
    val showBottomBar = route == Routes.Today

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = true,
                        onClick = { },
                        icon = { Text("•") },
                        label = { Text("Hôm nay") },
                    )
                    // Other tabs placeholder — inactive Wave 3
                    NavigationBarItem(
                        selected = false,
                        onClick = { },
                        enabled = false,
                        icon = { Text("○") },
                        label = { Text("Thư viện") },
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { },
                        enabled = false,
                        icon = { Text("○") },
                        label = { Text("Phát triển") },
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { },
                        enabled = false,
                        icon = { Text("○") },
                        label = { Text("Nhật ký") },
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { },
                        enabled = false,
                        icon = { Text("○") },
                        label = { Text("Thêm") },
                    )
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
                    onCompleted = { navController.popBackStack() },
                )
            }
        }
    }
}
