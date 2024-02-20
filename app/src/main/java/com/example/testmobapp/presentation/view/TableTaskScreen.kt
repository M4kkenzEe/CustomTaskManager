package com.example.testmobapp.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.testmobapp.data.model.TableTag
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.presentation.newview.CalendarRow
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import com.example.testmobapp.ui.theme.PurpleGrey40
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun TableTaskScreen(
    vm: TableViewModel = koinViewModel(),
    taskCLick: () -> Unit = {},
    popUpScreen: () -> Unit = {},
) {
    var createTaskState by remember {
        mutableStateOf(false)
    }

    var openTaskState by remember {
        mutableStateOf(false)
    }

    if (createTaskState) {
        CreateTaskDialog(tableViewModel = vm, onDismiss = { createTaskState = false })
    }

    if (openTaskState) {
        OpenTaskDialog(
            vm = vm,
            onDismiss = { openTaskState = false },
            popUpScreen = { popUpScreen() },
        )
    }

    val pagerState = rememberPagerState(
        pageCount = { Int.MAX_VALUE },
        initialPage = Int.MAX_VALUE / 2
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleGrey40),
    ) {
        TableTopBarView(
            addTaskCLick = { createTaskState = true },
            currentDateClick = {
                vm.todayIsState.value = LocalDate.now()
                coroutineScope.launch{ pagerState.animateScrollToPage(Int.MAX_VALUE / 2) }
            })

        CalendarRow(pagerState = pagerState)

        val tasksByCategory by vm.viewState.collectAsState()

        val notStartedTasks =
            tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.NOT_STARTED)
                ?.filter { it.createdAt == vm.todayIsState.value }
                ?: emptyList()
        val inProgressTasks =
            tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.IN_PROGRESS)
                ?.filter { it.createdAt == vm.todayIsState.value }
                ?: emptyList()
        val finishedTasks =
            tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.FINISHED)
                ?.filter { it.createdAt == vm.todayIsState.value }
                ?: emptyList()

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TaskListView(
                modifier = Modifier.weight(1f),
                vm = vm,
                taskList = notStartedTasks,
                columnLabel = "Не начатые",
                taskCLick = { },
                taskLongCLick = { openTaskState = true },
            )
            TaskListView(
                modifier = Modifier.weight(1f),
                taskList = inProgressTasks,
                vm = vm,
                columnLabel = "В работе",
                taskCLick = { },
                taskLongCLick = { openTaskState = true },
            )
            TaskListView(
                modifier = Modifier.weight(1f),
                taskList = finishedTasks,
                vm = vm,
                columnLabel = "Завершенные",
                taskCLick = { },
            )
        }
    }
}

@Composable
fun TaskListView(
    modifier: Modifier = Modifier,
    taskList: List<TaskDomain>,
    vm: TableViewModel,
    columnLabel: String = "Неопределенный",
    taskCLick: (TaskDomain) -> Unit = {},
    taskLongCLick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .border(BorderStroke(1.dp, Color.Gray)),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(taskList, key = { task -> task.id }) { task ->
                TaskItemScreen(
                    taskTitle = task.title,
                    taskDesc = task.description,
                    taskTag = task.tableTag,
                    taskCLick = {
                        vm.editTagTask(task)
                        taskCLick(task)
                    },
                    taskLongClick = {
                        vm.currentTaskState.value = task
                        taskLongCLick()
                    }
                )
            }
        }

        Text(
            text = columnLabel,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItemScreen(
    taskTitle: String = "Unknown title",
    taskDesc: String = "Desc",
    taskTag: TableTag,
    taskCLick: () -> Unit = {},
    taskLongClick: () -> Unit = {}
) {

    val cardColor = if (taskTag == TableTag.FINISHED) Color.Gray else Color.White
//    val textColor = Color.White

    val textStyle =
        if (taskTag == TableTag.FINISHED) TextStyle(textDecoration = TextDecoration.LineThrough)
        else TextStyle(textDecoration = TextDecoration.None)

    Box(
        modifier = Modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(20)
            )
            .background(cardColor)
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onClick = { taskCLick() },
                onLongClick = { taskLongClick() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = taskTitle,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = textStyle
            )
            Text(
                text = taskDesc,
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 10.sp,
                maxLines = 4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                textAlign = TextAlign.Start,
                style = textStyle
            )
            Text(
                text = taskTag.toString(),
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = textStyle
            )
        }
    }
}

@Composable
fun TableTopBarView(
    addTaskCLick: () -> Unit = {},
    currentDateClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(horizontal = 22.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Today is ${LocalDate.now().month} ${LocalDate.now().dayOfMonth}th",
            fontSize = 12.sp,
            color = PurpleGrey40,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clip(CircleShape)
                .padding(2.dp)
                .clickable { currentDateClick() }
        )

        Text(
            text = "+",
            fontSize = 20.sp,
            color = PurpleGrey40,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .clickable { addTaskCLick() }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskDialog(
    onDismiss: () -> Unit = {},
    tableViewModel: TableViewModel = koinViewModel()
) {
    var titleState by remember {
        mutableStateOf("New task")
    }

    var descState by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
                    .padding(horizontal = 22.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "X",
                    fontSize = 20.sp,
                    color = PurpleGrey40,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.clickable { onDismiss() }
                )

                Button(
                    onClick = {
                        tableViewModel.createTask(
                            taskDomain = TaskDomain(
                                id = 0,
                                title = titleState,
                                description = descState,
                                createdAt = tableViewModel.todayIsState.value
                            )
                        )
                        onDismiss()
                    },
                    modifier = Modifier.clip(RoundedCornerShape(20)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text(
                        text = "Добавить",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            TextField(
                value = titleState,
                onValueChange = { titleState = it },
                label = { Text(text = "Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            TextField(
                value = descState,
                onValueChange = { descState = it },
                label = { Text(text = "Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenTaskDialog(
    vm: TableViewModel = koinViewModel(),
    onDismiss: () -> Unit = {},
    popUpScreen: () -> Unit = {},
) {
    var titleState by remember {
        mutableStateOf(vm.currentTaskState.value?.title)
    }

    var descState by remember {
        mutableStateOf(vm.currentTaskState.value?.description)
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White)
                        .padding(horizontal = 22.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "X",
                        fontSize = 20.sp,
                        color = PurpleGrey40,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.clickable { onDismiss() }
                    )

                    Button(
                        onClick = {
                            vm.deleteTask()
                            onDismiss()
                        },
                        modifier = Modifier.clip(RoundedCornerShape(20)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                    ) {
                        Text(
                            text = "Удалить",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            vm.saveEditedTask(
                                title = titleState.toString(),
                                desc = descState.toString(),
                            )
                            onDismiss()
                        },
                        modifier = Modifier.clip(RoundedCornerShape(20)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    ) {
                        Text(
                            text = "Сохранить",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                TextField(
                    value = titleState.toString(),
                    onValueChange = { titleState = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 18.dp)

                )

                TextField(
                    value = descState.toString(),
                    onValueChange = { descState = it },
                    label = { Text(text = "Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 8.dp)

                )
            }

            item {
                Button(
                    onClick = {
                        vm.saveEditedTask(
                            title = titleState.toString(),
                            desc = descState.toString(),
                            tag = TableTag.IN_PROGRESS
                        )
                        onDismiss()
                        popUpScreen()
                    },
                    modifier = Modifier.clip(RoundedCornerShape(20)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                ) {
                    Text(
                        text = "Начать работу",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Button(
                    onClick = {
                        vm.finishTask()
                        onDismiss()
                    },
                    modifier = Modifier.clip(RoundedCornerShape(20)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                ) {
                    Text(
                        text = "Завершить",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                Text(text = "${vm.currentTaskState.value?.createdAt}")
            }
        }
    }
}


@Composable
@Preview
fun TableTaskViewPrev() {
    TableTaskScreen()
}