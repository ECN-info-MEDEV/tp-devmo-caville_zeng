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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.LearningApp.R
import com.example.LearningApp.data.OrderUiState
import com.example.LearningApp.ui.components.FormattedPriceLabel
import com.example.learningapp.data.StudyUiState
import com.example.learningapp.ui.theme.LearningTheme
import kotlinx.coroutines.flow.stateIn

/**
 * This is the composable for the study room screen
 */
@Composable
fun StudyRoomScreen(
    studyViewModel: StudyViewModel,
    modifier: Modifier = Modifier
) {
    var isPrivate=studyViewModel.uiState.studyDuration
    var studyDuration by remember { mutableStateOf("") }
    var breakDuration by remember { mutableStateOf("") }
    var roomName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val resources = LocalContext.current.resources

    //Load and format a string resource with the parameters.
    val studyInfo = stringResource(
        orderUiState.studyTime,
        orderUiState.roomName,
        orderUiState.breakTime,
        orderUiState.isPublic,
    )

}

@Preview
@Composable
fun StudyRoomPreview() {
    StudyRoomScreen(
        studyUiState = StudyUiState(false,60,10,"My Room", "mypass"),
        modifier = Modifier.fillMaxHeight()
    )
}
