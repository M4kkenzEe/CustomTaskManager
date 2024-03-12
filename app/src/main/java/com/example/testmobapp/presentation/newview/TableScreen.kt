package com.example.testmobapp.presentation.newview

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobapp.R
import com.example.testmobapp.data.model.TableTag
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.presentation.newview.references.AddFAB
import com.example.testmobapp.presentation.ui.theme.Coffee
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TableScreen(viewModel: TableViewModel = koinViewModel()) {

    val totalPages = 1000
    val pagerState = rememberPagerState(
        pageCount = { totalPages },
        initialPage = totalPages / 2
    )

    val tasksByCategory by viewModel.viewState.collectAsState()

    val notStartedTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.NOT_STARTED)
            ?.filter { it.createdAt == viewModel.todayIsState.value }?.reversed()
            ?: emptyList()
    val inProgressTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.IN_PROGRESS)?.reversed()
            ?.filter { it.createdAt == viewModel.todayIsState.value }
            ?: emptyList()
    val finishedTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.FINISHED)?.reversed()
            ?.filter { it.createdAt == viewModel.todayIsState.value }
            ?: emptyList()

    var sheetState by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            AddFAB(onClick = { sheetState = true })
        },
        containerColor = Color.White,
    ) {
        BottomSheetScreen(
            showBottomSheet = sheetState,
            cancelAdding = { sheetState = false },
            saveTask = { title, desc, priorityTag -> viewModel.addTask(title, desc, priorityTag) }
        )

        Column(modifier = Modifier.fillMaxSize()) {
            CalendarRow(
                pagerState = pagerState,
                totalPages = totalPages,
                backToCurrentDateClick = {
                    viewModel.todayIsState.value = LocalDate.now()
                    coroutineScope.launch { pagerState.animateScrollToPage(totalPages / 2) }
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_not_started),
                    taskList = notStartedTasks,
                    swipeToLeft = { viewModel.deleteTask1(it) },
                    swipeToRight = { viewModel.setTag(it, TableTag.IN_PROGRESS) }
                )
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_in_progress),
                    taskList = inProgressTasks,
                    swipeToLeft = { viewModel.setTag(it, TableTag.NOT_STARTED) },
                    swipeToRight = { viewModel.setTag(it, TableTag.FINISHED) }
                )
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_finished),
                    taskList = finishedTasks,
                    swipeToLeft = { viewModel.setTag(it, TableTag.IN_PROGRESS) },
                    swipeToRight = { viewModel.deleteTask1(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskColumn(
    modifier: Modifier = Modifier,
    label: String = "Не начатые",
    taskList: List<TaskDomain>,
    swipeToLeft: (task: TaskDomain) -> Unit = {},
    swipeToRight: (task: TaskDomain) -> Unit = {},
) {

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    DropdownMenu(
        expanded = isContextMenuVisible,
        onDismissRequest = { isContextMenuVisible = false },
        modifier = Modifier.height(50.dp),
        offset = DpOffset(100.dp, 100.dp)
    ) {
        DropdownMenuItem(
            text = { Text(text = "Copy", fontSize = 6.sp, fontWeight = FontWeight(400)) },
            onClick = { isContextMenuVisible = false },
            modifier = Modifier.height(20.dp)
        )
        DropdownMenuItem(
            text = { Text(text = "Delete", fontSize = 6.sp, fontWeight = FontWeight(400)) },
            onClick = { isContextMenuVisible = false },
            modifier = Modifier.height(20.dp)
        )
    }

    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .border(
                    BorderStroke(1.dp, Color.Black),
                    RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                )
                .background(Coffee)
                .height(24.dp)
                .padding(top = 2.dp),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black))
                .padding(top = 8.dp, start = 4.dp, end = 4.dp, bottom = 6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(taskList, key = { task -> task.id }) { task ->

                val swipeToDismissState = rememberSwipeToDismissBoxState()

                when (swipeToDismissState.dismissDirection) {
                    SwipeToDismissBoxValue.StartToEnd -> {
                        swipeToRight(task)
                    }

                    SwipeToDismissBoxValue.EndToStart -> {
                        swipeToLeft(task)
                    }

                    else -> {}
                }

                SwipeToDismissBox(
                    state = swipeToDismissState,
                    backgroundContent = {},
                ) {
                    CardTaskScreen(
                        taskTitle = task.title,
                        taskDesc = task.description,
                        taskStatus = task.tableTag,
                        onLongClick = { isContextMenuVisible = true },
                        taskPriority = task.priorityTag
                    )
                }
            }
        }

    }
}


@Composable
@Preview
fun TableScreenPrev() {
//    Row(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 16.dp)
//            .padding(top = 42.dp, bottom = 36.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        TaskColumn(modifier = Modifier.weight(1f))
//        TaskColumn(modifier = Modifier.weight(1f), label = "В работе")
//        TaskColumn(modifier = Modifier.weight(1f), label = "Завершенные")
//    }

    TableScreen()

}