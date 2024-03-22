package com.example.studyapp

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LearningAppScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") { MainScreen(navController) }
        composable("roomSettingScreen") { RoomSettingScreen(navController) }
        composable("studyRoomScreen") { StudyRoomScreen(navController) }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Button(onClick = { navController.navigate("roomSettingScreen") }) {
        Text(text = "Go to Room Settings")
    }
}


@Composable
fun RoomSettingScreen(navController: NavController) {

}

@Composable
fun StudyRoomScreen(navController: NavController) {

}


