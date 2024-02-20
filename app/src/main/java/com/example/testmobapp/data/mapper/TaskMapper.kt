package com.example.testmobapp.data.mapper

import com.example.testmobapp.data.model.TaskStatus
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.data.room.entities.TaskEntity
import java.time.LocalDate

object TaskMapper {

    fun mapEntityToDomain(taskEntity: TaskEntity): TaskDomain {
        return TaskDomain(
            id = taskEntity.id,
            title = taskEntity.title ?: "Unknown title",
            description = taskEntity.description ?: "U r lazy but",
            tableTag = TaskStatus.valueOf(taskEntity.tableTag ?: TaskStatus.NOT_STARTED.toString()),
            createdAt = LocalDate.parse(taskEntity.createdAt)
        )
    }

    fun mapDomainToEntity(taskModel: TaskDomain): TaskEntity {
        return TaskEntity(
            id = taskModel.id,
            title = taskModel.title,
            description = taskModel.description,
            tableTag = taskModel.tableTag.toString(),
            createdAt = taskModel.createdAt.toString()
        )

    }
}