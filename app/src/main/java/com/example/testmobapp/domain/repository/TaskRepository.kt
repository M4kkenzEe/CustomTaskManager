package com.example.testmobapp.domain.repository

import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.data.room.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAllTasks(): Flow<List<TaskEntity>>
    suspend fun insertTask(taskModel: TaskDomain)
    suspend fun editTask(taskModel: TaskDomain)
    suspend fun deleteTask(taskModel: TaskDomain)

}
