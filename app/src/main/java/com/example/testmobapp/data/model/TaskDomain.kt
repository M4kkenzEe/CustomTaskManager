package com.example.testmobapp.data.model

import androidx.compose.runtime.Immutable
import com.example.testmobapp.presentation.model.PriorityTag
import java.time.LocalDate

@Immutable
data class TaskDomain(
    val id: Int = 0,
    val title: String,
    val description: String,
    val tableTag: TableTag = TableTag.NOT_STARTED,
    val priorityTag: PriorityTag = PriorityTag.GREEN,
    val createdAt: LocalDate
)
