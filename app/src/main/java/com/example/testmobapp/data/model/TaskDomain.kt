package com.example.testmobapp.data.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class TaskDomain(
    val id: Int = 0,
    val title: String,
    val description: String,
    val tableTag: TaskStatus = TaskStatus.NOT_STARTED,
    val createdAt: LocalDate
)
