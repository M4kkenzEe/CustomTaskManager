package com.example.testmobapp.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobapp.data.model.TableTag
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.presentation.result.CreateTaskDialog
import com.example.testmobapp.presentation.result.OpenTaskDialog
import com.example.testmobapp.presentation.result.TableTopBarView
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import com.example.testmobapp.ui.theme.PurpleGrey40
import org.koin.androidx.compose.koinViewModel

@Composable
fun TableTaskScreen2(vm: TableViewModel = koinViewModel(), popUpScreen: () -> Unit = {}) {
    var isAddDialogOpenedState by remember {
        mutableStateOf(false)
    }

    var isTaskDialogOpenedState by remember {
        mutableStateOf(false)
    }

    if (isAddDialogOpenedState) {
        CreateTaskDialog(tableViewModel = vm, onDismiss = { isAddDialogOpenedState = false })
    }

    if (isTaskDialogOpenedState) {
        OpenTaskDialog(
            vm = vm,
            onDismiss = { isTaskDialogOpenedState = false },
            popUpScreen = { popUpScreen() },
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleGrey40),
    ) {
        TableTopBarView(addTaskCLick = { isAddDialogOpenedState = true })

        if (vm.tasksListState.value == null) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = Color.Blue
            )
        } else {

            val tasksByCategory by remember {
                vm.tasksListState
            }

            val notStartedTasks =
                tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.NOT_STARTED) ?: emptyList()
            val inProgressTasks =
                tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.IN_PROGRESS) ?: emptyList()
            val finishedTasks =
                tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.FINISHED) ?: emptyList()

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TaskListView2(
                    modifier = Modifier.weight(1f),
                    taskList = notStartedTasks,
                    columnLabel = "Не начатые",
                    taskCLick = { vm.getAllTasks() },
                    taskLongCLick = { isTaskDialogOpenedState = true },
                )
                TaskListView2(
                    modifier = Modifier.weight(1f),
                    taskList = inProgressTasks,
                    columnLabel = "В работе",
                    taskCLick = { vm.getAllTasks() },
                    taskLongCLick = { isTaskDialogOpenedState = true },
                )
                TaskListView2(
                    modifier = Modifier.weight(1f),
                    taskList = finishedTasks,
                    columnLabel = "Завершенные",
                    taskCLick = { },
                )
            }
        }
    }
}

@Composable
fun TaskListView2(
    modifier: Modifier = Modifier,
    taskList: List<TaskDomain>,
    columnLabel: String = "???",
    taskCLick: () -> Unit = {},
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
            taskList.forEach { task ->
                item {
                    TaskItemScreen2(
                        taskTitle = task.title,
                        taskDesc = task.description,
                        taskTag = task.tableTag,
                        taskCLick = {},
                        taskLongClick = {}
                    )
                }
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
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TaskItemScreen2(
    taskTitle: String = "Unknown title",
    taskDesc: String = "Desc",
    taskTag: TableTag,
    taskCLick: () -> Unit = {},
    taskLongClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(20)
            )
            .background(Color.White)
            .fillMaxWidth()
            .clickable { },

        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .fillMaxSize(),
        ) {
            Text(
                text = taskTitle,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
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
            )
            Text(
                text = taskTag.toString(),
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
@Preview
fun TableTaskScreen2Prev() {
    TableTaskScreen2()
}