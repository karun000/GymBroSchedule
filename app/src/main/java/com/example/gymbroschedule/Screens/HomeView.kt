package com.example.gymbroschedule.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymbroschedule.HomeAppBarView
import com.example.gymbroschedule.PreferenceHelper
import com.example.gymbroschedule.R
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.data.Database.GymDatabase
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.data.WeightRecord
import com.example.gymbroschedule.ui.theme.BottomNavigationBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeView(
    navController: NavController,
    gymViewModel: GymViewModel,
    gymDatabase: GymDatabase
) {
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var weightRecords by remember { mutableStateOf(emptyList<WeightRecord>()) }
    val gymList by remember { mutableStateOf(emptyList<Gym>()) }
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        fetchWeightRecords(gymDatabase) { records ->
            weightRecords = records
        }
    }

    Scaffold(
        contentColor = Color.White,
        backgroundColor = colorResource(id = R.color.background),
        modifier = Modifier.wrapContentSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        topBar = {
            HomeAppBarView(title = "User!")

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .offset(y = 70.dp)
                    .zIndex(1f)
                    .padding(start = 8.dp, end = 8.dp)
                    .clip(shape = RoundedCornerShape(25.dp)),
                backgroundColor = colorResource(id = R.color.home_text_card)
            ) {
                Row(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val context = LocalContext.current
                    val sharedPreferences: SharedPreferences =
                        context.getSharedPreferences("quotes_prefs", Context.MODE_PRIVATE)

                    val quotes = listOf(
                        "The only bad workout is the one that didn’t happen.",
                        "Strength doesn’t come from what you can do.",
                        "Your body can stand almost anything.",
                        "Don’t limit your challenges. Challenge your limits.",
                        "Success isn’t always about greatness.",
                        "Push yourself because no one else is going to do it for you.",
                        "Fitness is not about being better than someone else.",
                        "What seems impossible today will one day become your warm-up.",
                        "You don’t have to be extreme, just consistent.",
                        "The only way to achieve the impossible is to believe it is possible.",
                        "Your only limit is you.",
                        "Every workout is progress, no matter how small.",
                        "Strive for progress, not perfection.",
                        "The pain you feel today will be the strength you feel tomorrow.",
                        "It’s not about having time; it’s about making time.",
                        "Wake up with determination. Go to bed with satisfaction.",
                        "You don’t get what you wish for; you get what you work for.",
                        "Success is what happens after you have survived all of your mistakes.",
                        "If it doesn’t challenge you, it doesn’t change you.",
                        "Take care of your body. It’s the only place you have to live.",
                        "Don’t count the days; make the days count."
                    )

                    var currentQuoteIndex by remember {
                        mutableStateOf(sharedPreferences.getInt("last_quote_index", 0))
                    }

                    LaunchedEffect(Unit) {
                        val newIndex = (currentQuoteIndex + 1) % quotes.size
                        sharedPreferences.edit().putInt("last_quote_index", newIndex).apply()
                        currentQuoteIndex = newIndex
                    }

                    val currentQuote = quotes[currentQuoteIndex]
                    WelcomingText(text = currentQuote)
                }
            }
        },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 50.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                ) {
                    item {
                        ScheduleScreen(
                            viewModel = gymViewModel,
                            navController = navController
                        )
                    }

                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 25.dp, start = 8.dp, end = 8.dp)
                                .clip(shape = RoundedCornerShape(30.dp)),
                            backgroundColor = colorResource(id = R.color.home_card)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (weightRecords.isNotEmpty()) {
                                    LazyRow(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .weight(1f),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        val sortedRecords = weightRecords.sortedBy { it.date }
                                        items(sortedRecords) { record ->
                                            WeightRecordCard(record)
                                        }
                                    }
                                } else {
                                    Text(
                                        text = "No weight records available",
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .weight(1f),
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(viewModel: GymViewModel, navController: NavController) {
    val context = LocalContext.current
    val preferenceHelper = PreferenceHelper(context)
    var selectedWeek by remember { mutableStateOf("Week1") }
    val buttonPositions = remember { mutableStateOf(mutableMapOf("Week1" to 0f, "Week2" to 0f, "Week3" to 0f)) }
    var buttonWidth by remember { mutableStateOf(0f) }
    var containerWidth by remember { mutableStateOf(0f) }

    // Manual position mapping based on index (approximate, to be refined by actual positions)
    val positionMap = mapOf("Week1" to 0f, "Week2" to buttonWidth, "Week3" to buttonWidth * 2)
    val position = positionMap[selectedWeek] ?: 0f

    val rawOffset = if (containerWidth > 0 && buttonWidth > 0) {
        val centerPosition = position + (buttonWidth / 2)
        val maxOffset = containerWidth - buttonWidth
        val boundedOffset = centerPosition.coerceIn(0f, maxOffset) - (buttonWidth / 2)
        Log.d("ScheduleScreen", "Raw Offset: $boundedOffset, Center Position: $centerPosition, Position: $position, MaxOffset: $maxOffset, Container: $containerWidth, Button: $buttonWidth")
        boundedOffset
    } else {
        Log.d("ScheduleScreen", "Raw Offset defaulted to 0, Container: $containerWidth, Button: $buttonWidth")
        0f
    }
    val indicatorOffset by animateDpAsState(
        targetValue = rawOffset.dp,
        label = "indicatorOffset"
    )

    LaunchedEffect(Unit) {
        try {
            val savedWeek = preferenceHelper.getSelectedSchedule("selected_week")
            selectedWeek = savedWeek ?: "Week1"
            Log.d("ScheduleScreen", "Fetched week: $selectedWeek")
        } catch (e: Exception) {
            Log.e("ScheduleScreen", "Error fetching week", e)
            selectedWeek = "Week1"
        }

        // Check and update week every Saturday
        while (true) {
            val currentDate = LocalDate.now()
            if (currentDate.dayOfWeek.name == "SATURDAY") {
                val weeks = listOf("Week1", "Week2", "Week3")
                val currentIndex = weeks.indexOf(selectedWeek)
                val nextIndex = (currentIndex + 1) % weeks.size
                selectedWeek = weeks[nextIndex]
                preferenceHelper.saveSelectedSchedule("selected_week", selectedWeek)
                Log.d("ScheduleScreen", "Week updated to: $selectedWeek on Saturday")
            }
            delay(24 * 60 * 60 * 1000) // Check daily (24 hours)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(30.dp)),
        backgroundColor = colorResource(id = R.color.home_card)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(50.dp)
                    .onGloballyPositioned { coordinates ->
                        if (containerWidth == 0f) {
                            containerWidth = coordinates.size.width.toFloat()
                            Log.d("ScheduleScreen", "Container width set to: $containerWidth")
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .offset(x = indicatorOffset)
                        .width(buttonWidth.dp.coerceAtLeast(80.dp))
                        .height(50.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(colorResource(R.color.deep_blue))
                        .zIndex(0f)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(1f),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeekButton(
                        text = "Week1",
                        isSelected = selectedWeek == "Week1",
                        onClick = {
                            selectedWeek = "Week1"
                            preferenceHelper.saveSelectedSchedule("selected_week", "Week1")
                        },
                        onPositioned = { position, width ->
                            if (width > buttonWidth) buttonWidth = width
                        },
                        index = 0
                    )
                    WeekButton(
                        text = "Week2",
                        isSelected = selectedWeek == "Week2",
                        onClick = {
                            selectedWeek = "Week2"
                            preferenceHelper.saveSelectedSchedule("selected_week", "Week2")
                        },
                        onPositioned = { position, width ->
                            if (width > buttonWidth) buttonWidth = width
                        },
                        index = 1
                    )
                    WeekButton(
                        text = "Week3",
                        isSelected = selectedWeek == "Week3",
                        onClick = {
                            selectedWeek = "Week3"
                            preferenceHelper.saveSelectedSchedule("selected_week", "Week3")
                        },
                        onPositioned = { position, width ->
                            if (width > buttonWidth) buttonWidth = width
                        },
                        index = 2
                    )
                }
            }

            GymList(viewModel = viewModel, navController = navController, selectedWeek = selectedWeek)
        }
    }
}

@Composable
fun WeekButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onPositioned: (Float, Float) -> Unit,
    modifier: Modifier = Modifier,
    index: Int
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(50.dp)
            .widthIn(min = 80.dp)
            .clickable { onClick() }
            .background(if (isSelected) colorResource(R.color.deep_blue) else Color.Transparent)
            .onGloballyPositioned { coordinates ->
                val position = coordinates.positionInParent().x
                val width = coordinates.size.width.toFloat()
                onPositioned(position, width)
            }
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else colorResource(R.color.outter_card),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}


@Composable
fun WeightRecordCard(record: WeightRecord) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)),
        backgroundColor = colorResource(id = R.color.weight_card)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Date: ${record.date}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Day: ${record.dayOfWeek}",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Weight: ${record.weight} kg",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GymList(viewModel: GymViewModel, navController: NavController, selectedWeek: String) {
    val gymList by viewModel.getAllInfo.collectAsState(initial = emptyList())
    val currentDayOfWeek = LocalDate.now().dayOfWeek.name

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        val matchingGyms = gymList.filter { it.Day.equals(currentDayOfWeek, ignoreCase = true) }

        if (matchingGyms.isNotEmpty()) {
            items(matchingGyms) { gym ->
                Column {
                    Text(
                        text = "Today's Workout",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Card(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(20.dp))
                            .padding(top = 8.dp),
                        backgroundColor = Color.Transparent
                    ) {
                        todayWorkout(
                            gym = gym,
                            onClick = { /* Handle click event */ },
                            navController = navController,
                            viewModel = viewModel,
                            selectedWeek = selectedWeek
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomingText(text: String, typingSpeed: Long = 100) {
    var displayedText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        displayedText = ""
        for (char in text) {
            displayedText += char
            delay(typingSpeed)
        }
        delay(1000)
    }

    Text(text = displayedText, fontSize = 18.sp)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun todayWorkout(
    gym: Gym,
    onClick: () -> Unit,
    navController: NavController,
    viewModel: GymViewModel,
    selectedWeek: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.outter_card),
        onClick = { }
    ) {
        Column {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = gym.Day,
                            color = Color.Black,
                            fontFamily = FontFamily.Default,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row {
                            Text(
                                text = "${gym.Workout} - Workout",
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black)
                    )
                }
            }

            var cardSize by remember { mutableStateOf(Size.Zero) }
            var scale by remember { mutableStateOf(1f) }
            val originalScale = 1f
            val originalOffset = Offset(0f, 0f)
            var offset by remember { mutableStateOf(Offset(0f, 0f)) }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .onSizeChanged { size ->
                        cardSize = Size(size.width.toFloat(), size.height.toFloat())
                    }
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                event.changes.forEach { change ->
                                    if (change.isConsumed) return@forEach
                                    if (event.changes.size == 2) {
                                        val zoom = event.calculateZoom()
                                        scale = (scale * zoom).coerceIn(1f, 3f)
                                    }
                                    val pan = event.changes.first().positionChange()
                                    offset = Offset(
                                        (offset.x + pan.x).coerceIn(
                                            -cardSize.width * (scale - 1f),
                                            cardSize.width * (scale - 1f)
                                        ),
                                        (offset.y + pan.y).coerceIn(
                                            -cardSize.height * (scale - 1f),
                                            cardSize.height * (scale - 1f)
                                        )
                                    )
                                }
                                event.changes.firstOrNull { it.pressed.not() }?.let {
                                    scale = originalScale
                                    offset = originalOffset
                                }
                            }
                        }
                    }
            ) {
                Card(
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp, bottom = 4.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    backgroundColor = colorResource(id = R.color.inner_card)
                ) {
                    Column(
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 5.dp, bottom = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Exercises",
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Helper function to display exercise row
                        @Composable
                        fun ExerciseRow(
                            index: Int,
                            exercise: String
                        ) {
                            if (exercise.isNotEmpty()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "$index. $exercise",
                                        color = Color.Black,
                                        fontSize = 18.sp,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                                Spacer(modifier = Modifier.padding(6.dp))
                            }
                        }

                        // Select exercises based on the selected week
                        val exercises = when (selectedWeek) {
                            "Week1" -> listOf(
                                gym.Exercise1, gym.Exercise2, gym.Exercise3, gym.Exercise4,
                                gym.Exercise5, gym.Exercise6, gym.Exercise7, gym.Exercise8,
                                gym.Exercise9, gym.Exercise10, gym.Exercise11, gym.Exercise12
                            )
                            "Week2" -> listOf(
                                gym.Exercise1R2, gym.Exercise2R2, gym.Exercise3R2, gym.Exercise4R2,
                                gym.Exercise5R2, gym.Exercise6R2, gym.Exercise7R2, gym.Exercise8R2,
                                gym.Exercise9R2, gym.Exercise10R2, gym.Exercise11R2, gym.Exercise12R2
                            )
                            "Week3" -> listOf(
                                gym.Exercise1R3, gym.Exercise2R3, gym.Exercise3R3, gym.Exercise4R3,
                                gym.Exercise5R3, gym.Exercise6R3, gym.Exercise7R3, gym.Exercise8R3,
                                gym.Exercise9R3, gym.Exercise10R3, gym.Exercise11R3, gym.Exercise12R3
                            )
                            else -> listOf()
                        }

                        // Split exercises into two columns
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                exercises.take(6).forEachIndexed { index, exercise ->
                                    ExerciseRow(index + 1, exercise)
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                exercises.drop(6).forEachIndexed { index, exercise ->
                                    ExerciseRow(index + 7, exercise)
                                }
                            }
                        }

                        // Additional Exercises in LazyRow
                        val extraExercises = listOf(
                            "1 - ${gym.Extra1}" to gym.Extra1,
                            "2 - ${gym.Extra2}" to gym.Extra2,
                            "3 - ${gym.Extra3}" to gym.Extra3,
                            "4 - ${gym.Extra4}" to gym.Extra4,
                            "5 - ${gym.Extra5}" to gym.Extra5
                        ).filter { it.second.isNotEmpty() }

                        if (extraExercises.isNotEmpty()) {
                            Spacer(modifier = Modifier.padding(4.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(colorResource(id = R.color.inner_card))
                                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Text("Additional Exercise: ${gym.ExtraExercise}", color = Color.Black, fontSize = 18.sp)
                                    LazyRow(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        items(extraExercises) { (label, _) ->
                                            Box(
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(colorResource(id = R.color.inner_card))
                                                    .padding(8.dp)
                                            ) {
                                                Text(
                                                    text = label,
                                                    color = Color.Black,
                                                    fontSize = 18.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.padding(2.dp))
                        }
                        else {
                            Spacer(modifier = Modifier.padding(2.dp))
                            Divider()
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            val diplomaFont = FontFamily(Font(R.font.diploma))
                            Text(
                                text = "The End!",
                                fontFamily = diplomaFont,
                                fontSize = 25.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun fetchWeightRecords(gymDatabase: GymDatabase, onResult: (List<WeightRecord>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val records = gymDatabase.weightDao().getWeightRecordsByScheduleId(1L)
        onResult(records)
    }
}