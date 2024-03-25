package com.example.studyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.studyapp.data.StudyUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class StudyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(StudyUiState())
    val uiState: StateFlow<StudyUiState> = _uiState


    private val _timerState = MutableStateFlow(TimerState())
    val timerState: StateFlow<TimerState> = _timerState

    private var timerJob: Job? = null

    fun onIsPrivateChanged(isPrivate: Boolean) {
        _uiState.value = _uiState.value.copy(isPrivate = isPrivate)
    }

    fun onStudyDurationChanged(duration: String) {
        _uiState.value = _uiState.value.copy(studyDuration = duration)
    }

    fun onBreakDurationChanged(duration: String) {
        _uiState.value = _uiState.value.copy(breakDuration = duration)
    }

    fun onRoomNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(roomName = name)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun startTimer() {

        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            val studyDurationMillis = _uiState.value.studyDuration.toLongOrNull()?.times(60 * 1000) ?: 0L
            val startTime = System.currentTimeMillis()
            val endTime = startTime + studyDurationMillis

            _timerState.value = _timerState.value.copy(startTime = startTime, duration = studyDurationMillis, isRunning = true)

            while (System.currentTimeMillis() < endTime) {
                val currentTime = System.currentTimeMillis()
                _timerState.value = _timerState.value.copy(timeInMillis = currentTime - startTime)
                delay(1000)
            }

            pauseTimer()
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        _timerState.value = _timerState.value.copy(isRunning = false)
    }

    fun resetTimer() {
        pauseTimer()
        _timerState.value = TimerState()
    }

    data class TimerState(
        val startTime: Long = 0L,
        val timeInMillis: Long = 0L,
        val duration: Long = 60 * 1000,
        val isRunning: Boolean = false
    ) {
        val progress: Float
            get() = if (duration > 0) timeInMillis.toFloat() / duration else 0f
        val totalTime: String
            get() {
                val totalSeconds = timeInMillis / 1000
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60
                return String.format("%02d:%02d", minutes, seconds)
            }
    }
}
