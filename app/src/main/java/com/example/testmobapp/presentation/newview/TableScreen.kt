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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobapp.R
import com.example.testmobapp.data.model.TableTag
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.presentation.newview.utils.AddFAB
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TableScreen(viewModel: TableViewModel = koinViewModel()) {
    val pagerState = rememberPagerState(
        pageCount = { Int.MAX_VALUE },
        initialPage = Int.MAX_VALUE / 2
    )

    val tasksByCategory by viewModel.viewState.collectAsState()

    val notStartedTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.NOT_STARTED)
            ?.filter { it.createdAt == viewModel.todayIsState.value }
            ?: emptyList()
    val inProgressTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.IN_PROGRESS)
            ?.filter { it.createdAt == viewModel.todayIsState.value }
            ?: emptyList()
    val finishedTasks =
        tasksByCategory?.groupBy { it.tableTag }?.get(TableTag.FINISHED)
            ?.filter { it.createdAt == viewModel.todayIsState.value }
            ?: emptyList()

    Scaffold(
        floatingActionButton = { AddFAB(onClick = {}) },
        containerColor = Color.White,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CalendarRow(pagerState = pagerState)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_not_started),
                    taskList = notStartedTasks
                )
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_in_progress),
                    taskList = inProgressTasks
                )
                TaskColumn(
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.label_finished),
                    taskList = finishedTasks
                )
            }
        }
    }
}

@Composable
fun TaskColumn(
    modifier: Modifier = Modifier,
    label: String = "Не начатые",
    taskList: List<TaskDomain>
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .background(Color.Black)
                .height(24.dp)
                .padding(top = 6.dp),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black))
                .padding(top = 6.dp, start = 4.dp, end = 4.dp, bottom = 6.dp)
        ) {
            items(taskList, key = { task -> task.id }) { task ->
                CardTaskScreen(
                    taskTitle = task.title,
                    taskDesc = task.description,
                    taskStatus = task.tableTag,
                )
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