package com.example.testmobapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private val DEFAULT_TIME = minToSec(90)

    var showButtonsState = mutableStateOf(true)

    var timeState = mutableStateOf(DEFAULT_TIME)

    private var job: Job? = null


    private fun startTimer(secsInput: Int) {
        job = viewModelScope.launch {
            timeState.value = secsInput
            repeat(secsInput) {
                delay(1000)
                timeState.value -= 1
            }
        }
    }

    fun pressStartButton() {
        cancelTimer()
        startTimer(timeState.value)
    }

    fun pressUnpauseButton() {
        if (job?.isActive != true) {
            startTimer(timeState.value)
        }
    }

    fun pressPauseButton() {
        stopTimer()
    }

    fun pressDropButton() {
        cancelTimer()
    }

    private fun stopTimer() {
        if (job != null) {
            job?.cancel()

        }
    }

    private fun cancelTimer() {
        stopTimer()
        timeState.value = DEFAULT_TIME
    }

    private fun minToSec(mins: Int): Int {
        return mins * 60
    }

}