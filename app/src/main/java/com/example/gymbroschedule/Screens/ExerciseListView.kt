package com.example.gymbroschedule.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.gymbroschedule.AppBarView
import com.example.gymbroschedule.R
import com.example.gymbroschedule.ViewModel.GymViewModel
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.ui.theme.BottomNavigationBar

@Composable
fun ExerciseView(
    navController: NavController,
    viewModel: GymViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(1) }

    Scaffold(
        topBar = { AppBarView(title = "Schedules") { navController.navigateUp() } },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        val gymList = viewModel.getAllInfo.collectAsState(initial = listOf())
        val gyms = remember { mutableStateOf(gymList.value.toMutableList()) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(gymList.value, key = { gym -> gym.id }) { gym ->
                GymItem(
                    gym = gym,
                    navController = navController,
                    viewModel = viewModel,
                    onClick = {
                        val id = gym.id
                        navController.navigate(Screen.AddScreen.route + "/$id")
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GymItem(
    gym: Gym,
    onClick: () -> Unit,
    navController: NavController,
    viewModel: GymViewModel
) {
    var isExpanded by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        backgroundColor = colorResource(id = R.color.outter_card),
        onClick = { isExpanded = !isExpanded }
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

                    Row(horizontalArrangement = Arrangement.End) {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier
                                .background(color = colorResource(id = R.color.weight_card))
                                .clip(RoundedCornerShape(12.dp)),
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Row {
                                        Icon(
                                            modifier = Modifier.padding(bottom = 5.dp),
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit",
                                            tint = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(text = "Edit", color = Color.White)
                                    }
                                },
                                onClick = {
                                    navController.navigate(Screen.AddScreen.route + "/${gym.id}")
                                    showMenu = false
                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Row {
                                        Icon(
                                            modifier = Modifier.padding(bottom = 5.dp),
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(text = "Delete", color = Color.White)
                                    }
                                },
                                onClick = {
                                    showDialog = true
                                    showMenu = false
                                }
                            )
                        }

                        if (showDialog) {
                            AnimatedVisibility(
                                visible = showDialog,
                                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                            ) {
                                AlertDialog(
                                    onDismissRequest = { showDialog = false },
                                    title = {
                                        Text(
                                            text = "Confirm Deletion",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 18.sp
                                        )
                                    },
                                    text = {
                                        Text(
                                            text = "Are you sure you want to delete this Schedule?",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    },
                                    confirmButton = {
                                        Button(
                                            onClick = {
                                                viewModel.deleteAnInfo(gym)
                                                showDialog = false
                                            },
                                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                                        ) {
                                            Text("Delete")
                                        }
                                    },
                                    dismissButton = {
                                        Button(
                                            onClick = { showDialog = false },
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = colorResource(id = R.color.Sky_Blue)
                                            )
                                        ) {
                                            Text("Cancel")
                                        }
                                    }
                                )
                            }
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
                    if (isExpanded) {
                        Text(text = "Tap to Collapse", color = Color.Black)
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Collapse"
                        )
                    } else {
                        Text(text = "Tap to view all Exercises", color = Color.Black)
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Expand"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(Color.Black)
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(durationMillis = 800)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 100))
            ) {
                var cardSize by remember { mutableStateOf(Size.Zero) }
                var scale by remember { mutableStateOf(1f) }
                val originalScale = 1f
                val originalOffset = Offset(0f, 0f)
                var offset by remember { mutableStateOf(Offset(0f, 0f)) }

                // Define hasR2 and hasR3 in the outer scope
                val hasR2 = listOf(
                    gym.Exercise1R2, gym.Exercise2R2, gym.Exercise3R2, gym.Exercise4R2,
                    gym.Exercise5R2, gym.Exercise6R2, gym.Exercise7R2, gym.Exercise8R2,
                    gym.Exercise9R2, gym.Exercise10R2, gym.Exercise11R2, gym.Exercise12R2
                ).any { it.isNotEmpty() }
                val hasR3 = listOf(
                    gym.Exercise1R3, gym.Exercise2R3, gym.Exercise3R3, gym.Exercise4R3,
                    gym.Exercise5R3, gym.Exercise6R3, gym.Exercise7R3, gym.Exercise8R3,
                    gym.Exercise9R3, gym.Exercise10R3, gym.Exercise11R3, gym.Exercise12R3
                ).any { it.isNotEmpty() }

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
                            .fillMaxSize()
                            .padding(start = 4.dp, end = 4.dp, bottom = 4.dp),
                        backgroundColor = colorResource(id = R.color.inner_card)
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 5.dp, bottom = 8.dp)
                        ) {
                            if (isExpanded) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Exercises",
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.Black
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "1st week",
                                        modifier = Modifier.weight(1f),
                                        fontWeight = FontWeight.SemiBold,
                                        textDecoration = TextDecoration.Underline,
                                        color = Color.Black
                                    )
                                    if (hasR2) {
                                        Divider(
                                            modifier = Modifier
                                                .width(2.dp)
                                                .height(20.dp)
                                                .background(Color.Black)
                                        )
                                        Spacer(modifier = Modifier.width(22.dp))
                                        Text(
                                            text = "2nd week",
                                            modifier = Modifier.weight(1f),
                                            fontWeight = FontWeight.SemiBold,
                                            textDecoration = TextDecoration.Underline,
                                            color = Color.Black
                                        )
                                    }
                                    if (hasR3) {
                                        Divider(
                                            modifier = Modifier
                                                .width(2.dp)
                                                .height(20.dp)
                                                .background(Color.Black)
                                        )
                                        Spacer(modifier = Modifier.width(22.dp))
                                        Text(
                                            text = "3rd week",
                                            modifier = Modifier.weight(1f),
                                            fontWeight = FontWeight.SemiBold,
                                            textDecoration = TextDecoration.Underline,
                                            color = Color.Black
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Helper function to display exercise row
                                @Composable
                                fun ExerciseRow(
                                    index: Int,
                                    exercise: String,
                                    exerciseR2: String,
                                    exerciseR3: String,
                                    hasR2: Boolean,
                                    hasR3: Boolean
                                ) {
                                    if (exercise.isNotEmpty()) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "$index. $exercise",
                                                color = Color.Black,
                                                modifier = Modifier.weight(1f)
                                            )
                                            if (hasR2) {
                                                Divider(
                                                    modifier = Modifier
                                                        .width(2.dp)
                                                        .height(20.dp)
                                                        .background(Color.Black)
                                                )
                                                Spacer(modifier = Modifier.width(22.dp))
                                                Text(
                                                    text = if (exerciseR2.isNotEmpty()) "$index. $exerciseR2" else "",
                                                    color = Color.Black,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }
                                            if (hasR3) {
                                                Divider(
                                                    modifier = Modifier
                                                        .width(2.dp)
                                                        .height(20.dp)
                                                        .background(Color.Black)
                                                )
                                                Spacer(modifier = Modifier.width(22.dp))
                                                Text(
                                                    text = if (exerciseR3.isNotEmpty()) "$index. $exerciseR3" else "",
                                                    color = Color.Black,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(6.dp))
                                    }
                                }

                                // Display exercises 1 to 12
                                ExerciseRow(1, gym.Exercise1, gym.Exercise1R2, gym.Exercise1R3, hasR2, hasR3)
                                ExerciseRow(2, gym.Exercise2, gym.Exercise2R2, gym.Exercise2R3, hasR2, hasR3)
                                ExerciseRow(3, gym.Exercise3, gym.Exercise3R2, gym.Exercise3R3, hasR2, hasR3)
                                ExerciseRow(4, gym.Exercise4, gym.Exercise4R2, gym.Exercise4R3, hasR2, hasR3)
                                ExerciseRow(5, gym.Exercise5, gym.Exercise5R2, gym.Exercise5R3, hasR2, hasR3)
                                ExerciseRow(6, gym.Exercise6, gym.Exercise6R2, gym.Exercise6R3, hasR2, hasR3)
                                ExerciseRow(7, gym.Exercise7, gym.Exercise7R2, gym.Exercise7R3, hasR2, hasR3)
                                ExerciseRow(8, gym.Exercise8, gym.Exercise8R2, gym.Exercise8R3, hasR2, hasR3)
                                ExerciseRow(9, gym.Exercise9, gym.Exercise9R2, gym.Exercise9R3, hasR2, hasR3)
                                ExerciseRow(10, gym.Exercise10, gym.Exercise10R2, gym.Exercise10R3, hasR2, hasR3)
                                ExerciseRow(11, gym.Exercise11, gym.Exercise11R2, gym.Exercise11R3, hasR2, hasR3)
                                ExerciseRow(12, gym.Exercise12, gym.Exercise12R2, gym.Exercise12R3, hasR2, hasR3)

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
                                            Text("Additional Exercise: ${gym.ExtraExercise}", color = Color.Black)
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
                                                            fontSize = 14.sp
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                    }
                                    Spacer(modifier = Modifier.padding(2.dp))
                                }
                                else{
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Divider()
                                }


                                Spacer(modifier = Modifier.padding(4.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
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
    }
}