package com.example.testmobapp.data.model

data class TaskDomain(
    val id: Int = 0,
    var title: String,
    val description: String,
    val tableTag: TableTag = TableTag.NOT_STARTED
)
