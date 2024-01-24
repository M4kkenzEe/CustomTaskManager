package com.example.testmobapp.presenter.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.presenter.viewmodel.TableViewModel
import org.koin.androidx.compose.koinViewModel

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

        items(viewState?: emptyList(),
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
            Button(onClick = { vm.createTask(TaskDomain(0, "title${r}", "decc $r")) }) {

            }
        }
    }
}

@Composable
@Preview
fun TestScreenPrev() {
    TestScreen()
}