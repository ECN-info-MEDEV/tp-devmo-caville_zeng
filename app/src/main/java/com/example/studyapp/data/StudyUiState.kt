package com.example.studyapp.data

data class StudyUiState(
    val isPrivate: Boolean = false,
    val studyDuration: String = "",
    val breakDuration: String = "",
    val roomName: String = "",
    val password: String = ""
)
