package com.example.gymbroschedule.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gymbroschedule.AppBarView
import com.example.gymbroschedule.R
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.ui.theme.BottomNavigationBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: GymViewModel,
    navController: NavController
) {
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var selectedIndex by remember { mutableStateOf(-1) }
    var isGymDayError by remember { mutableStateOf(false) }
    var isGymWorkoutError by remember { mutableStateOf(false) }
    var isExercise1Error by remember { mutableStateOf(false) }

    // Populate ViewModel with existing Gym data if editing (id != 0L)
    if (id != 0L) {
        val gym = viewModel.getAnInfoById(id)
            .collectAsState(initial = Gym(0L, "", "", "", "", "", "", ""))
        viewModel.gymDayState = gym.value.Day
        viewModel.gymWorkoutState = gym.value.Workout
        viewModel.Exercise1 = gym.value.Exercise1
        viewModel.Exercise1R2 = gym.value.Exercise1R2
        viewModel.Exercise1R3 = gym.value.Exercise1R3
        viewModel.Exercise2 = gym.value.Exercise2
        viewModel.Exercise2R2 = gym.value.Exercise2R2
        viewModel.Exercise2R3 = gym.value.Exercise2R3
        viewModel.Exercise3 = gym.value.Exercise3
        viewModel.Exercise3R2 = gym.value.Exercise3R2
        viewModel.Exercise3R3 = gym.value.Exercise3R3
        viewModel.Exercise4 = gym.value.Exercise4
        viewModel.Exercise4R2 = gym.value.Exercise4R2
        viewModel.Exercise4R3 = gym.value.Exercise4R3
        viewModel.Exercise5 = gym.value.Exercise5
        viewModel.Exercise5R2 = gym.value.Exercise5R2
        viewModel.Exercise5R3 = gym.value.Exercise5R3
        viewModel.Exercise6 = gym.value.Exercise6
        viewModel.Exercise6R2 = gym.value.Exercise6R2
        viewModel.Exercise6R3 = gym.value.Exercise6R3
        viewModel.Exercise7 = gym.value.Exercise7
        viewModel.Exercise7R2 = gym.value.Exercise7R2
        viewModel.Exercise7R3 = gym.value.Exercise7R3
        viewModel.Exercise8 = gym.value.Exercise8
        viewModel.Exercise8R2 = gym.value.Exercise8R2
        viewModel.Exercise8R3 = gym.value.Exercise8R3
        viewModel.Exercise9 = gym.value.Exercise9
        viewModel.Exercise9R2 = gym.value.Exercise9R2
        viewModel.Exercise9R3 = gym.value.Exercise9R3
        viewModel.Exercise10 = gym.value.Exercise10
        viewModel.Exercise10R2 = gym.value.Exercise10R2
        viewModel.Exercise10R3 = gym.value.Exercise10R3
        viewModel.Exercise11 = gym.value.Exercise11
        viewModel.Exercise11R2 = gym.value.Exercise11R2
        viewModel.Exercise11R3 = gym.value.Exercise11R3
        viewModel.Exercise12 = gym.value.Exercise12
        viewModel.Exercise12R2 = gym.value.Exercise12R2
        viewModel.Exercise12R3 = gym.value.Exercise12R3
        viewModel.ExtraExercise = gym.value.ExtraExercise
        viewModel.Extra1 = gym.value.Extra1
        viewModel.Extra2 = gym.value.Extra2
        viewModel.Extra3 = gym.value.Extra3
        viewModel.Extra4 = gym.value.Extra4
        viewModel.Extra5 = gym.value.Extra5
    } else {
        // Reset ViewModel fields for new entry
        viewModel.gymDayState = ""
        viewModel.gymWorkoutState = ""
        viewModel.Exercise1 = ""
        viewModel.Exercise1R2 = ""
        viewModel.Exercise1R3 = ""
        viewModel.Exercise2 = ""
        viewModel.Exercise2R2 = ""
        viewModel.Exercise2R3 = ""
        viewModel.Exercise3 = ""
        viewModel.Exercise3R2 = ""
        viewModel.Exercise3R3 = ""
        viewModel.Exercise4 = ""
        viewModel.Exercise4R2 = ""
        viewModel.Exercise4R3 = ""
        viewModel.Exercise5 = ""
        viewModel.Exercise5R2 = ""
        viewModel.Exercise5R3 = ""
        viewModel.Exercise6 = ""
        viewModel.Exercise6R2 = ""
        viewModel.Exercise6R3 = ""
        viewModel.Exercise7 = ""
        viewModel.Exercise7R2 = ""
        viewModel.Exercise7R3 = ""
        viewModel.Exercise8 = ""
        viewModel.Exercise8R2 = ""
        viewModel.Exercise8R3 = ""
        viewModel.Exercise9 = ""
        viewModel.Exercise9R2 = ""
        viewModel.Exercise9R3 = ""
        viewModel.Exercise10 = ""
        viewModel.Exercise10R2 = ""
        viewModel.Exercise10R3 = ""
        viewModel.Exercise11 = ""
        viewModel.Exercise11R2 = ""
        viewModel.Exercise11R3 = ""
        viewModel.Exercise12 = ""
        viewModel.Exercise12R2 = ""
        viewModel.Exercise12R3 = ""
        viewModel.ExtraExercise = ""
        viewModel.Extra1 = ""
        viewModel.Extra2 = ""
        viewModel.Extra3 = ""
        viewModel.Extra4 = ""
        viewModel.Extra5 = ""
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var isClicked by remember { mutableStateOf(false) }

    Scaffold(
        contentColor = Color.White,
        backgroundColor = colorResource(id = R.color.background),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isClicked = true
                    // Check if required fields are empty
                    isGymDayError = viewModel.gymDayState.isEmpty()
                    isGymWorkoutError = viewModel.gymWorkoutState.isEmpty()
                    isExercise1Error = viewModel.Exercise1.isEmpty()

                    if (!isGymDayError && !isGymWorkoutError && !isExercise1Error) {
                        val gym = Gym(
                            id = id,
                            Day = viewModel.gymDayState.trim(),
                            Workout = viewModel.gymWorkoutState.trim(),
                            Exercise1 = viewModel.Exercise1.trim(),
                            Exercise1R2 = viewModel.Exercise1R2.trim(),
                            Exercise1R3 = viewModel.Exercise1R3.trim(),
                            Exercise2 = viewModel.Exercise2.trim(),
                            Exercise2R2 = viewModel.Exercise2R2.trim(),
                            Exercise2R3 = viewModel.Exercise2R3.trim(),
                            Exercise3 = viewModel.Exercise3.trim(),
                            Exercise3R2 = viewModel.Exercise3R2.trim(),
                            Exercise3R3 = viewModel.Exercise3R3.trim(),
                            Exercise4 = viewModel.Exercise4.trim(),
                            Exercise4R2 = viewModel.Exercise4R2.trim(),
                            Exercise4R3 = viewModel.Exercise4R3.trim(),
                            Exercise5 = viewModel.Exercise5.trim(),
                            Exercise5R2 = viewModel.Exercise5R2.trim(),
                            Exercise5R3 = viewModel.Exercise5R3.trim(),
                            Exercise6 = viewModel.Exercise6.trim(),
                            Exercise6R2 = viewModel.Exercise6R2.trim(),
                            Exercise6R3 = viewModel.Exercise6R3.trim(),
                            Exercise7 = viewModel.Exercise7.trim(),
                            Exercise7R2 = viewModel.Exercise7R2.trim(),
                            Exercise7R3 = viewModel.Exercise7R3.trim(),
                            Exercise8 = viewModel.Exercise8.trim(),
                            Exercise8R2 = viewModel.Exercise8R2.trim(),
                            Exercise8R3 = viewModel.Exercise8R3.trim(),
                            Exercise9 = viewModel.Exercise9.trim(),
                            Exercise9R2 = viewModel.Exercise9R2.trim(),
                            Exercise9R3 = viewModel.Exercise9R3.trim(),
                            Exercise10 = viewModel.Exercise10.trim(),
                            Exercise10R2 = viewModel.Exercise10R2.trim(),
                            Exercise10R3 = viewModel.Exercise10R3.trim(),
                            Exercise11 = viewModel.Exercise11.trim(),
                            Exercise11R2 = viewModel.Exercise11R2.trim(),
                            Exercise11R3 = viewModel.Exercise11R3.trim(),
                            Exercise12 = viewModel.Exercise12.trim(),
                            Exercise12R2 = viewModel.Exercise12R2.trim(),
                            Exercise12R3 = viewModel.Exercise12R3.trim(),
                            ExtraExercise = viewModel.ExtraExercise.trim(),
                            Extra1 = viewModel.Extra1.trim(),
                            Extra2 = viewModel.Extra2.trim(),
                            Extra3 = viewModel.Extra3.trim(),
                            Extra4 = viewModel.Extra4.trim(),
                            Extra5 = viewModel.Extra5.trim()
                        )
                        if (id != 0L) {
                            viewModel.updateAnInfo(gym)
                        } else {
                            viewModel.addAnInfo(gym)
                        }
                        scope.launch {
                            delay(1000L)
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please fill the required fields.")
                        }
                    }
                },
                modifier = Modifier
                    .imePadding()
                    .padding(16.dp),
                backgroundColor = colorResource(id = R.color.floating_button_green)
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = "Save"
                )
            }
        },
        modifier = Modifier.wrapContentSize(),
        topBar = {
            AppBarView(
                title = if (id != 0L) stringResource(id = R.string.update_workouts)
                else stringResource(id = R.string.add_workouts)
            ) {
                navController.navigateUp()
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .background(colorResource(id = R.color.home_app_bar)),
        ) {
            item {
                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        backgroundColor = colorResource(id = R.color.home_card)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            gymHeader(
                                label = "Day",
                                value = viewModel.gymDayState,
                                onValueChanged = { viewModel.onGymDayChanged(it) },
                                isError = isGymDayError
                            )

                            gymHeader(
                                label = "Workout",
                                value = viewModel.gymWorkoutState,
                                onValueChanged = { viewModel.onGymWorkoutChanged(it) },
                                isError = isGymWorkoutError
                            )
                        }
                    }
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 70.dp, end = 70.dp)
                ) {
                    Text(
                        text = "Week 1",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Week 2",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Week 3",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    backgroundColor = colorResource(id = R.color.home_card)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        for (index in 1..12) { // Extended to 12 exercises
                            gymTextField(
                                label = "Variations",
                                value = when (index) {
                                    1 -> viewModel.Exercise1
                                    2 -> viewModel.Exercise2
                                    3 -> viewModel.Exercise3
                                    4 -> viewModel.Exercise4
                                    5 -> viewModel.Exercise5
                                    6 -> viewModel.Exercise6
                                    7 -> viewModel.Exercise7
                                    8 -> viewModel.Exercise8
                                    9 -> viewModel.Exercise9
                                    10 -> viewModel.Exercise10
                                    11 -> viewModel.Exercise11
                                    12 -> viewModel.Exercise12
                                    else -> ""
                                },
                                value2 = when (index) {
                                    1 -> viewModel.Exercise1R2
                                    2 -> viewModel.Exercise2R2
                                    3 -> viewModel.Exercise3R2
                                    4 -> viewModel.Exercise4R2
                                    5 -> viewModel.Exercise5R2
                                    6 -> viewModel.Exercise6R2
                                    7 -> viewModel.Exercise7R2
                                    8 -> viewModel.Exercise8R2
                                    9 -> viewModel.Exercise9R2
                                    10 -> viewModel.Exercise10R2
                                    11 -> viewModel.Exercise11R2
                                    12 -> viewModel.Exercise12R2
                                    else -> ""
                                },
                                value3 = when (index) {
                                    1 -> viewModel.Exercise1R3
                                    2 -> viewModel.Exercise2R3
                                    3 -> viewModel.Exercise3R3
                                    4 -> viewModel.Exercise4R3
                                    5 -> viewModel.Exercise5R3
                                    6 -> viewModel.Exercise6R3
                                    7 -> viewModel.Exercise7R3
                                    8 -> viewModel.Exercise8R3
                                    9 -> viewModel.Exercise9R3
                                    10 -> viewModel.Exercise10R3
                                    11 -> viewModel.Exercise11R3
                                    12 -> viewModel.Exercise12R3
                                    else -> ""
                                },
                                onValueChanged = {
                                    when (index) {
                                        1 -> viewModel.onExercise1Changed(it)
                                        2 -> viewModel.onExercise2Changed(it)
                                        3 -> viewModel.onExercise3Changed(it)
                                        4 -> viewModel.onExercise4Changed(it)
                                        5 -> viewModel.onExercise5Changed(it)
                                        6 -> viewModel.onExercise6Changed(it)
                                        7 -> viewModel.onExercise7Changed(it)
                                        8 -> viewModel.onExercise8Changed(it)
                                        9 -> viewModel.onExercise9Changed(it)
                                        10 -> viewModel.onExercise10Changed(it)
                                        11 -> viewModel.onExercise11Changed(it)
                                        12 -> viewModel.onExercise12Changed(it)
                                    }
                                },
                                onValueChanged2 = {
                                    when (index) {
                                        1 -> viewModel.onExercise1R2Changed(it)
                                        2 -> viewModel.onExercise2R2Changed(it)
                                        3 -> viewModel.onExercise3R2Changed(it)
                                        4 -> viewModel.onExercise4R2Changed(it)
                                        5 -> viewModel.onExercise5R2Changed(it)
                                        6 -> viewModel.onExercise6R2Changed(it)
                                        7 -> viewModel.onExercise7R2Changed(it)
                                        8 -> viewModel.onExercise8R2Changed(it)
                                        9 -> viewModel.onExercise9R2Changed(it)
                                        10 -> viewModel.onExercise10R2Changed(it)
                                        11 -> viewModel.onExercise11R2Changed(it)
                                        12 -> viewModel.onExercise12R2Changed(it)
                                    }
                                },
                                onValueChanged3 = {
                                    when (index) {
                                        1 -> viewModel.onExercise1R3Changed(it)
                                        2 -> viewModel.onExercise2R3Changed(it)
                                        3 -> viewModel.onExercise3R3Changed(it)
                                        4 -> viewModel.onExercise4R3Changed(it)
                                        5 -> viewModel.onExercise5R3Changed(it)
                                        6 -> viewModel.onExercise6R3Changed(it)
                                        7 -> viewModel.onExercise7R3Changed(it)
                                        8 -> viewModel.onExercise8R3Changed(it)
                                        9 -> viewModel.onExercise9R3Changed(it)
                                        10 -> viewModel.onExercise10R3Changed(it)
                                        11 -> viewModel.onExercise11R3Changed(it)
                                        12 -> viewModel.onExercise12R3Changed(it)
                                    }
                                },
                                exerciseNum = index
                            )
                        }
                    }
                }
            }

            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    extraExercise(
                        label = "Extra Exercises",
                        value = viewModel.ExtraExercise,
                        onValueChanged = { viewModel.onExtraExerciseChanged(it) }
                    )
                    extraExercise(
                        label = "Extra 1",
                        value = viewModel.Extra1,
                        onValueChanged = { viewModel.onExtra1Changed(it) }
                    )
                    extraExercise(
                        label = "Extra 2",
                        value = viewModel.Extra2,
                        onValueChanged = { viewModel.onExtra2Changed(it) }
                    )
                    extraExercise(
                        label = "Extra 3",
                        value = viewModel.Extra3,
                        onValueChanged = { viewModel.onExtra3Changed(it) }
                    )
                    extraExercise(
                        label = "Extra 4",
                        value = viewModel.Extra4,
                        onValueChanged = { viewModel.onExtra4Changed(it) }
                    )
                    extraExercise(
                        label = "Extra 5",
                        value = viewModel.Extra5,
                        onValueChanged = { viewModel.onExtra5Changed(it) }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun gymTextField(
    label: String,
    value: String,
    value2: String,
    value3: String,
    exerciseNum: Int,
    onValueChanged: (String) -> Unit,
    onValueChanged2: (String) -> Unit,
    onValueChanged3: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray)
            )
            Text(
                text = "Exercise $exerciseNum",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color.Gray)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChanged,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                label = { Text(text = label, color = colorResource(id = R.color.Text)) },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.Text),
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = colorResource(id = R.color.Text),
                    focusedBorderColor = colorResource(id = R.color.Text),
                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                    cursorColor = colorResource(id = R.color.Text),
                    focusedLabelColor = colorResource(id = R.color.Text),
                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                )
            )
            OutlinedTextField(
                value = value2,
                onValueChange = onValueChanged2,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                label = { Text(text = label, color = colorResource(id = R.color.Text)) },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.Text),
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = colorResource(id = R.color.Text),
                    focusedBorderColor = colorResource(id = R.color.Text),
                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                    cursorColor = colorResource(id = R.color.Text),
                    focusedLabelColor = colorResource(id = R.color.Text),
                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                )
            )
            OutlinedTextField(
                value = value3,
                onValueChange = onValueChanged3,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                label = { Text(text = label, color = colorResource(id = R.color.Text)) },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.Text),
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = colorResource(id = R.color.Text),
                    focusedBorderColor = colorResource(id = R.color.Text),
                    unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                    cursorColor = colorResource(id = R.color.Text),
                    focusedLabelColor = colorResource(id = R.color.Text),
                    unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                )
            )
            Spacer(modifier = Modifier.height((-15).dp))
        }
    }
}

@Composable
fun extraExercise(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(start = 8.dp, end = 8.dp),
        backgroundColor = colorResource(id = R.color.home_card)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            Row(modifier = Modifier.padding(8.dp)) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChanged,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = label, color = colorResource(id = R.color.Text))
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    textStyle = TextStyle(
                        color = colorResource(id = R.color.Text),
                        fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = colorResource(id = R.color.Text),
                        focusedBorderColor = colorResource(id = R.color.Text),
                        unfocusedBorderColor = colorResource(id = R.color.deep_blue),
                        cursorColor = colorResource(id = R.color.Text),
                        focusedLabelColor = colorResource(id = R.color.Text),
                        unfocusedLabelColor = colorResource(id = R.color.deep_blue)
                    )
                )
            }
        }
    }
}

@Composable
fun gymHeader(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label, color = if (isError) Color.Red else Color.White)
        },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
        textStyle = TextStyle(
            color = colorResource(id = R.color.Text),
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.Text),
            focusedBorderColor = if (isError) Color.Red else colorResource(id = R.color.Text),
            unfocusedBorderColor = if (isError) Color.Red else Color.Black,
            cursorColor = colorResource(id = R.color.Text),
            focusedLabelColor = colorResource(id = R.color.Text),
            unfocusedLabelColor = Color.White
        )
    )
}
