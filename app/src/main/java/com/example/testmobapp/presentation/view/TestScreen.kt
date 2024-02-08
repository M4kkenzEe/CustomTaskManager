package com.example.testmobapp.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestScreen(vm: TableViewModel = koinViewModel()) {

    val viewState by vm.viewState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(viewState ?: emptyList(),
            key = { task -> task.id }
        ) { task ->
            Box(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(20.dp)
                    .combinedClickable(
                        onClick = { vm.editTagTask(task) },
                        onLongClick = { vm.deleteTask1(task) }),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = task.title, fontSize = 16.sp)
                    Text(
                        text = task.tableTag.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }

        item {
            val r = (0..1000).random()
            Button(onClick = {
                vm.createTask(
                    TaskDomain(
                        0,
                        "title${r}",
                        "decc $r",
                        createdAt = LocalDate.now()
                    )
                )
            }) {

            }
        }
    }
}

@Composable
fun WeeklyCalendarView() {
    // State to hold the current week's starting date
    val currentWeekStartDate = LocalDate.now().with(java.time.DayOfWeek.MONDAY)

    // Calendar header with the week's starting date
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Today is ${currentWeekStartDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))}",
            style = MaterialTheme.typography.headlineSmall
        )

        // Week's days
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
        ) {

//            itemsIndexed(items())
            (0..6).forEach { dayIndex ->
                val currentDate = currentWeekStartDate.plusDays(dayIndex.toLong())
                item { CalendarDay(date = currentDate) }
            }
        }
    }
}

@Composable
fun CalendarDay(date: LocalDate) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .clickable { }
            .zIndex(2f)
            .size(46.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CalendarScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

    }

}

@Composable
@Preview
fun TestScreenPrev() {
//    TestScreen()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { WeeklyCalendarView() }
}