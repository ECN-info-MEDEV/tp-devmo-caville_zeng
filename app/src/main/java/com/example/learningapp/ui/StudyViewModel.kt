/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.learningapp.ui

import androidx.lifecycle.ViewModel
import com.example.learningapp.data.StudyUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/** Duration before break */
private const val DURATION_BEFORE_BREAK = 60

/**
 * [StudyViewModel] holds information about a study session.
 */
class StudyViewModel : ViewModel() {

    /**
     * Learning state for this room
     */
    private val _uiState = MutableStateFlow(StudyUiState())
    val uiState: StateFlow<StudyUiState> = _uiState.asStateFlow()

    /**
     * Set the study duration for this order's state
     */
    fun setStudyTime(durationStudy: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                studyTime = durationStudy
            )
        }
    }

    /**
     * Set the break duration for this order's state
     */
    fun setBreakTime(durationBreak: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                breakTime = durationBreak
            )
        }
    }

    /**
     * Set the room state (public or private) for this order's state
     */
    fun setRoomState(isPublic: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                isPublic=isPublic
            )
        }
    }

    /**
     * Reset the order state
     */
    fun resetOrder() {
        _uiState.value = StudyUiState()
    }
}
