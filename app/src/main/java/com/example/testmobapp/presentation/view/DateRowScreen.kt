package com.example.testmobapp.presentation.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun CalendarDay(
    number: String,
    isClicked: Boolean,
    onClick: () -> Unit = {},
) {
    val bgColor = if (isClicked) Color.White else Color.Black
    val textColor = if (isClicked) Color.Black else Color.White

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(35.dp)
            .background(bgColor)
            .zIndex(1f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            fontSize = 16.sp,
            color = textColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun WeekScreen(currentWeekStartDate: LocalDate) {
    val vm: TableViewModel = koinViewModel()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly
    ) {
        for (day in 0..6) {
            val currentDay = currentWeekStartDate.plusDays(day.toLong())
            CalendarDay(
                number = "${currentDay.dayOfMonth}",
                isClicked = currentDay == vm.todayIsState.value,
                onClick = { vm.todayIsState.value = currentDay }
            )
            Log.d("CalendarTest", "${currentDay.month} - ${currentWeekStartDate.month}")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarRow(modifier: Modifier = Modifier) {
    val pageState = rememberPagerState(
        pageCount = { Int.MAX_VALUE },
        initialPage = Int.MAX_VALUE / 2
    )
    HorizontalPager(
        state = pageState,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 12.dp)
    ) {
        val currentWeekStartDate = calculateWeekStartDate(pageState.currentPage)
        WeekScreen(currentWeekStartDate = currentWeekStartDate)
    }
}

fun calculateWeekStartDate(currentPage: Int): LocalDate {
    return LocalDate.now().with(java.time.DayOfWeek.MONDAY)
        .plusWeeks(currentPage.toLong() - Int.MAX_VALUE / 2)
}

@Composable
@Preview
fun TestScreen2Prev() {
    CalendarRow()
}