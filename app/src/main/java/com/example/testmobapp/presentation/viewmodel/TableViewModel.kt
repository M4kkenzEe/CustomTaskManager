package com.example.testmobapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmobapp.data.mapper.TaskMapper.mapEntityToDomain
import com.example.testmobapp.data.model.TaskStatus
import com.example.testmobapp.data.model.TaskDomain
import com.example.testmobapp.domain.interactor.TaskInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.time.LocalDate

class TableViewModel(private val taskInteractor: TaskInteractor) : ViewModel() {

    val currentTaskState = mutableStateOf<TaskDomain?>(null)

    private val _viewState: MutableStateFlow<List<TaskDomain>?> = MutableStateFlow(null)
    val viewState: StateFlow<List<TaskDomain>?> get() = _viewState

    val todayIsState = mutableStateOf(LocalDate.now())

    private fun getAllTasks() {
        viewModelScope.launch {
            taskInteractor.getAllTasks()
                .flowOn(Dispatchers.IO)
                .catch {
                    Log.d(
                        "DB",
                        "Error (getAllTasks):\n${it.message}\n${it.localizedMessage}"
                    )
                }
                .collect { it ->
                    Log.d("DB", "Success! (getAllTasks)\n$it")
                    _viewState.value = it.map { mapEntityToDomain(it) }
                }
        }
    }

    fun saveEditedTask(
        title: String,
        desc: String,
        tag: TaskStatus = currentTaskState.value?.tableTag!!,
    ) {
        val resultTask =
            TaskDomain(
                id = currentTaskState.value?.id!!,
                title = title,
                description = desc,
                tableTag = tag,
                createdAt = currentTaskState.value?.createdAt!!
            )
        viewModelScope.launch {
            try {
                taskInteractor.editTask(resultTask)
            } catch (e: Exception) {
                Log.d("DB", "Error! (saveEditedTask)\n${e.message}\n${e.localizedMessage}")
            }
            currentTaskState.value = null
            getAllTasks()
        }
    }

    fun finishTask() {
        val task = TaskDomain(
            id = currentTaskState.value?.id!!,
            title = currentTaskState.value?.title!!,
            description = currentTaskState.value?.description!!,
            tableTag = TaskStatus.FINISHED,
            createdAt = currentTaskState.value?.createdAt!!
        )
        viewModelScope.launch {
            taskInteractor.editTask(task)
        }
        getAllTasks()
    }

    fun editTagTask(taskDomain: TaskDomain) {
        val resultTask = TaskDomain(
            id = taskDomain.id,
            title = taskDomain.title,
            description = taskDomain.description,
            tableTag = when (taskDomain.tableTag) {
                TaskStatus.NOT_STARTED -> TaskStatus.IN_PROGRESS
                TaskStatus.IN_PROGRESS -> TaskStatus.NOT_STARTED
                else -> TaskStatus.NOT_STARTED
            },
            createdAt = taskDomain.createdAt
        )

        viewModelScope.launch(Dispatchers.IO) {
            try {
                taskInteractor.editTask(resultTask)
            } catch (e: Exception) {
                Log.d("DB", "Error! (editTask)\n${e.message}\n${e.localizedMessage}")
            }
        }
        getAllTasks()
    }

    fun createTask(taskDomain: TaskDomain) {
        viewModelScope.launch {
            try {
                taskInteractor.insertTask(taskDomain)
            } catch (e: Exception) {
                Log.d("DB", "Error! (createTask)\n${e.message}\n${e.localizedMessage}")
            }
        }
        getAllTasks()
    }

    fun deleteTask() {
        viewModelScope.launch {
            try {
                taskInteractor.deleteTask(currentTaskState.value!!)
            } catch (e: Exception) {
                Log.d("DB", "Error! (deleteTask)\n${e.message}\n${e.localizedMessage}")
            }
            currentTaskState.value = null
            getAllTasks()
        }
    }

    fun doSomething1(s:String) {

    }

    fun doSomething2() {

    }

    init {
        getAllTasks()
    }

}