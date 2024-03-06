package com.example.testmobapp.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var title: String?,
    @ColumnInfo var description: String?,
    @ColumnInfo var tableTag: String?,
    @ColumnInfo var priorityTag: String?,
    @ColumnInfo var createdAt: String
)