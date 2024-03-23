package com.example.studyapp.data

data class StudyUiState(
    val isPrivate: Boolean = true,
    val studyDuration: String = "60",
    val breakDuration: String = "10",
    val roomName: String = "",
    val password: String = ""
)
