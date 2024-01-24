package com.example.testmobapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testmobapp.data.model.TableTag
import com.example.testmobapp.data.room.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAllData(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(vararg: TaskEntity)

    @Query("UPDATE task_table SET title = :title, description = :description, tableTag = :tag WHERE id = :id")
    suspend fun editTask(id: Int, title: String, description: String, tag: String)

    @Query("DELETE FROM task_table WHERE id = :id")
    suspend fun deleteTask(id: Int)
}