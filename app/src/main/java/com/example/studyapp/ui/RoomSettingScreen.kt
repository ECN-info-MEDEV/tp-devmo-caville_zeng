package com.example.studyapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyapp.R
import com.example.studyapp.ui.theme.BLUE1
import com.example.studyapp.ui.theme.Blue500
import com.example.studyapp.viewmodel.StudyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun RoomSettingScreen(viewModel: StudyViewModel = viewModel()) {
    MainScreen(viewModel = viewModel)
}
@Composable
fun MainScreen(viewModel: StudyViewModel) {
    var isPrivate by remember { mutableStateOf(viewModel.uiState.value.isPrivate) }
    var studyDuration by remember { mutableStateOf(viewModel.uiState.value.studyDuration) }
    var breakDuration by remember { mutableStateOf(viewModel.uiState.value.breakDuration) }
    var roomName by remember { mutableStateOf(viewModel.uiState.value.roomName) }
    var password by remember { mutableStateOf(viewModel.uiState.value.password) }
    Checkbox(checked = isPrivate, onCheckedChange = {
        isPrivate = it
        viewModel.onIsPrivateChanged(it)
    })

    Scaffold(bottomBar = { BottomNavigationBar() }) { innerPadding ->
        Column(
            modifier = Modifier.padding(0.dp)

        ) {

            SearchBar()
            TaskList()
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, color = BLUE1, shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Column {
                    LearningSettings(isPrivate,
                        onPrivateChange = { isPrivate = it },
                        studyDuration,
                        onStudyDurationChange = { studyDuration = it },
                        breakDuration,
                        onBreakDurationChange = { breakDuration = it },
                        roomName,
                        onRoomNameChange = { roomName = it },
                        password,
                        onPasswordChange = { password = it })
                    Column(

                        modifier = Modifier
                            .padding(20.dp)
                            .border(
                                width = 1.dp, color = BLUE1, shape = RoundedCornerShape(10.dp)
                            ),
                    ) {
                        DurationField("Study duration", studyDuration, onValueChange = {})
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .height(1.dp)
                                .background(color = BLUE1)
                        )
                        DurationField("Break time", breakDuration, onValueChange = {})

                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .width(150.dp)
                            .height(50.dp)
                            .background(Blue500 ,shape = RoundedCornerShape(15.dp))

                            .align(CenterHorizontally)
                    ) {
                        Text("Start", color = Color.White, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}


@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(45.dp),

        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                "Search the information",
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(30.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun TaskList() {

    Text(
        "TODAY'S SCHEDULE",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    CheckboxListItem(text = "Recite 30 English words")
    CheckboxListItem(text = "Listen to English for an hour")
}

@Composable
fun CheckboxListItem(text: String) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(5.dp, 0.dp, 5.dp, 0.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
        Spacer(modifier = Modifier.width(5.dp))
        Text(text, fontSize = 12.sp, color = Color.Black)
    }
}


@Composable
fun LearningSettings(
    isPrivate: Boolean,
    onPrivateChange: (Boolean) -> Unit,
    studyDuration: String,
    onStudyDurationChange: (String) -> Unit,
    breakDuration: String,
    onBreakDurationChange: (String) -> Unit,
    roomName: String,
    onRoomNameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .border(
                width = 1.dp, color = BLUE1, shape = RoundedCornerShape(10.dp)
            ),

        ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "Learning settings",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = roomName,
                onValueChange = onRoomNameChange,
                placeholder = { Text("Enter the name of the study room") },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.White,
                    disabledIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.background(color = Color.Transparent)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = BLUE1)
            )
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Text("  Is it private?")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Yes")
                Checkbox(checked = isPrivate, onCheckedChange = {
                    onPrivateChange(it)
                })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "No")
                Checkbox(checked = !isPrivate, onCheckedChange = {
                    onPrivateChange(!it)
                })

            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = BLUE1)
            )
            if (isPrivate) {
                Spacer(modifier = Modifier.height(8.dp))
                PasswordField(password, onPasswordChange)
            }

        }
    }
}

@Composable
fun DurationField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        placeholder = { Text(label) },
        trailingIcon = {
            Row {
                Text(text = "...min", color = Color.Black)
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24),
                    contentDescription = ""
                )
            }

        })
}

@Composable
fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.White,
            disabledIndicatorColor = Color.Transparent,
        ),
        onValueChange = onPasswordChange,
        placeholder = { Text("Enter Password") },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun BottomNavigationBar() {
    var currentTab by remember {
        mutableStateOf(0)
    }
    val tabs = listOf(
        "Home" to R.drawable.ic_baseline_home_24,
        "Statistics" to R.drawable.ic_baseline_query_stats_24,
        "Creation" to R.drawable.ic_outline_add_box_24,
        "Settings" to R.drawable.ic_baseline_person_24
    )
    BottomNavigation(contentColor = Color.Black, backgroundColor = Color.White) {
        tabs.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.second),
                        contentDescription = ""
                    )
                }, label = {
                    Text(text = item.first)
                },
                selected = currentTab == index,
                onClick = {
                    currentTab = index
                }
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun RoomSettingScreenPreview() {
    RoomSettingScreen()
}
