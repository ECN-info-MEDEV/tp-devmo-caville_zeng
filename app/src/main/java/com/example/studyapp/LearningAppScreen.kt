package com.example.studyapp

import StudyRoomScreen
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.ui.MainScreen
import com.example.studyapp.ui.RoomSettingScreen
import com.example.studyapp.ui.StudyViewModel

enum class LearningScreens(@StringRes val title: Int){
    Home(title=R.string.app_name),
    Setting(title = R.string.setting_screen),
    SingleRoom(title=R.string.single_room)
}

@Composable
fun LearningAppBar(
    currentScreen: LearningScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LearningAppScreen(
    studyViewModel: StudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LearningScreens.valueOf(
        backStackEntry?.destination?.route ?: LearningScreens.Home.name
    )
    Scaffold(
        topBar = {
            LearningAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    )
    { innerPadding ->
        val uiState by studyViewModel.uiState.collectAsState()

        NavHost(
            navController = navController, startDestination = LearningScreens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(LearningScreens.Home.name) { MainScreen(navController) }
            composable(LearningScreens.Setting.name) { RoomSettingScreen(studyViewModel) }
            composable(LearningScreens.SingleRoom.name) { StudyRoomScreen(studyViewModel) }
        }
    }
}


