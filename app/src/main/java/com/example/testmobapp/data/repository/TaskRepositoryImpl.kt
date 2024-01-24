package com.example.testmobapp.data.repository

import com.example.testmobapp.data.mapper.TaskMapper.mapDomainToEntity
import com.example.testmobapp.data.mapper.TaskMapper.mapEntityToDomain
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.domain.repository.TaskRepository
import com.example.testmobapp.data.room.dao.TaskDao
import com.example.testmobapp.data.room.entities.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {


    override suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllData()
    }

    override suspend fun insertTask(taskModel: TaskDomain) {
        taskDao.insertTask(mapDomainToEntity(taskModel))
    }

    override suspend fun editTask(taskModel: TaskDomain) {
        taskDao.editTask(
            id = taskModel.id,
            title = taskModel.title,
            description = taskModel.description,
            tag = taskModel.tableTag.toString()
        )
    }

    override suspend fun deleteTask(taskModel: TaskDomain) {
        taskDao.deleteTask(taskModel.id)
    }

}