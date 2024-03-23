package com.example.studyapp.ui

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.studyapp.LearningScreens

@Composable
fun MainScreen(navController: NavHostController) {
    Button(onClick = { navController.navigate(LearningScreens.Setting.name) }) {
        Text(text = "Go to Room Settings")
    }
}