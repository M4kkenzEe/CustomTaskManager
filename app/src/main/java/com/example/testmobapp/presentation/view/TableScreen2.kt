package com.example.testmobapp.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobapp.data.model.TableTag
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun TableScreen2(vm: TableViewModel = koinViewModel()) {

    var taskListState by remember {
        vm.tasksListState
    }

    var currentTaskState by remember {
        vm.currentTaskState
    }

    var taskDialogState by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        taskListState?.forEach { task ->
            item {
                TaskItem(
                    taskDomain = task,
                    onClick = { vm.currentTaskState.value = task },
                    onLongClick = {
                        vm.currentTaskState.value = task
                        taskDialogState = true
                    },
                    viewModel = vm
                )
            }
        }

        item {
            Button(
                onClick = {
                    val r = (0..900).random()
                    vm.createTask(
                        TaskDomain(
                            id = 0,
                            title = "titleState $r",
                            description = "descState $r",
                            createdAt = LocalDate.now()
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.Blue)
            ) {
                Text(
                    text = "Добавить",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    taskDomain: TaskDomain,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
//    onTagSave: (TaskDomain) -> Unit = {},
    viewModel: TableViewModel
) {

    var tableTagState by remember {
        mutableStateOf(taskDomain.tableTag)
    }

    Box(modifier = Modifier
        .border(BorderStroke(1.dp, color = Color.Blue))
        .clip(RoundedCornerShape(20))
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(16.dp)
        .combinedClickable(
            onLongClick = { onLongClick() },
            onClick = {
                tableTagState = if (tableTagState == TableTag.NOT_STARTED) TableTag.IN_PROGRESS
                else TableTag.NOT_STARTED
                viewModel.saveEditedTask(
                    taskDomain.title,
                    taskDomain.description,
                    tableTagState
                )
//                onTagSave(
//                    TaskDomain(
//                        taskDomain.id,
//                        taskDomain.title,
//                        taskDomain.description,
//                        tableTagState
//                    )
//                )
//                onClick()
            }
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = taskDomain.title,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = taskDomain.description,
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
                text = tableTagState.toString()
//                taskDomain.tableTag.toString()
//                taskDomain.tableTag.toString()
                ,
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
fun TableScreen2Prev() {
    TableScreen2()
}