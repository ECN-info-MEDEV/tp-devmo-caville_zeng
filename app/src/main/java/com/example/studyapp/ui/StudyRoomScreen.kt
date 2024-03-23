import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.studyapp.LearningScreens
import com.example.studyapp.R
import com.example.studyapp.ui.StudyViewModel
import com.example.studyapp.ui.theme.Blue500
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun StudyRoomScreen(studyViewModel: StudyViewModel = viewModel(), navController: NavController = rememberNavController()) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                onBack = {
                    //Handle back press
                    navController.navigate(LearningScreens.Setting.name)
                },
                onClose = {
                    //Handle close action
                    navController.navigate(LearningScreens.Home.name)
                }
            )
        },
        bottomBar = { BottomStudyBar(navController) }
    ) {innerPadding ->
        SingleStudyScreen(studyViewModel)
    }
}

@Composable
fun CustomTopAppBar(onBack: () -> Unit, onClose: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {

            Spacer(Modifier.weight(1f, true))
            Text(
                text = "Timer",
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize * 1.2 // 增加20%的字号大小
                )
            )
            Spacer(Modifier.weight(1f, true))
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close"
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentColor = LocalContentColor.current
    )
}

@Composable
fun StudyRoomTitle(viewModel: StudyViewModel, isPrivate: Boolean, roomName: String) {

    val imageResource = R.drawable.ic_profile
    val backgroundColor = Color(0xFFE0E0E0)
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(40.dp)
        )

        Text(
            text=if (isPrivate) "Private Room "+roomName else "Public Room "+roomName,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            modifier=Modifier.fillMaxWidth(),
            textAlign=TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun TimerControlButtons(viewModel: StudyViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        IconButton(onClick = { viewModel.resetTimer() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_reset),
                contentDescription = "Reset Timer",
                modifier = Modifier
                    .size(100.dp)

                    .padding(12.dp)
            )
        }


        IconButton(onClick = {  }) {
            Image(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = "Pause Timer",
                modifier = Modifier
                    .size(100.dp)

                    .padding(12.dp)
            )
        }


        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.ic_repeat),
                contentDescription = "Repeat Timer",
                modifier = Modifier
                    .size(96.dp)

                    .padding(12.dp)
            )
        }
    }
}


@Composable
fun SingleStudyScreen(viewModel: StudyViewModel = viewModel()) {
    val buttonColors = ButtonDefaults.buttonColors(backgroundColor = Blue500 , contentColor = Color.White)
    var isPrivate by remember { mutableStateOf(viewModel.uiState.value.isPrivate) }
    var studyDuration by remember { mutableStateOf(viewModel.uiState.value.studyDuration) }
    var breakDuration by remember { mutableStateOf(viewModel.uiState.value.breakDuration) }
    var roomName by remember { mutableStateOf(viewModel.uiState.value.roomName) }
    var password by remember { mutableStateOf(viewModel.uiState.value.password) }
    val timerState by viewModel.timerState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.startTimer()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StudyRoomTitle(viewModel, isPrivate, roomName)
        Spacer(modifier = Modifier.height(50.dp))

        Box(contentAlignment = Alignment.Center) {
            Canvas(modifier = Modifier.size(200.dp)) {

                drawCircle(
                    color = Color.LightGray,
                    radius = size.minDimension / 2
                )

                val sweepAngle = 0.7f * 360f
                drawArc(
                    brush = SolidColor(Color.Blue),
                    startAngle = -90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = 12.dp.toPx(), cap = StrokeCap.Round),
                    size = this.size
                )
            }
            Text(
                text = formatTime(timerState.progress),
                style = MaterialTheme.typography.h4
            )
        }

        TimerControlButtons(viewModel = viewModel)

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Blue500)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Total time spent: "+timerState.totalTime,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.subtitle1.fontSize * 1.35
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = { viewModel.startTimer() },
                colors = buttonColors,
                modifier = Modifier.weight(1f)
            ) {
                Text("Study")
            }


            Button(
                onClick = { viewModel.pauseTimer() },
                colors = buttonColors,
                modifier = Modifier.weight(1f)
            ) {
                Text("Rest")
            }
        }

    }
}

fun formatTime(seconds: Float): String {
    val minutes = (seconds/60).toInt()
    val remainingSeconds = (seconds %60).toInt()
    return String.format("%02d:%02d", minutes, remainingSeconds)
}

@Composable
fun BottomStudyBar(navController: NavController) {
    var currentTab by remember {
        mutableStateOf(0)
    }
    val tabs = listOf(
        "Home" to R.drawable.ic_baseline_home_24,
        "Notes" to R.drawable.notes_svgrepo_com,
        "Share" to R.drawable.add_friend_svgrepo_com,
        "Settings" to R.drawable.ic_baseline_person_24
    )
    BottomNavigation(contentColor = Color.Black, backgroundColor = Color.White) {
        tabs.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.second), contentDescription = "image") },
                label = { Text(text = item.first) },
                selected = currentTab == index,
                onClick = { currentTab = index },
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StudyRoomScreen()
}