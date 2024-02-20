package com.example.testmobapp.data.model

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class TaskDomain(
    val id: Int = 0,
    var title: String,
    val description: String,
    val tableTag: TableTag = TableTag.NOT_STARTED,
    val createdAt: LocalDate
)
