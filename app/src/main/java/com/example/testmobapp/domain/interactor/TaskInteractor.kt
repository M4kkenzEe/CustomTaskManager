package com.example.testmobapp.domain.interactor

import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.domain.repository.TaskRepository
import com.example.testmobapp.data.room.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskInteractor(private val taskRepository: TaskRepository) {

    suspend fun insertTask(taskModel: TaskDomain) {
        taskRepository.insertTask(taskModel = taskModel)
    }

    suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskRepository.getAllTasks()
    }

    suspend fun deleteTask(taskDomain: TaskDomain) {
        taskRepository.deleteTask(taskModel = taskDomain)
    }

    suspend fun editTask(taskDomain: TaskDomain) {
        taskRepository.editTask(taskModel = taskDomain)
    }
}