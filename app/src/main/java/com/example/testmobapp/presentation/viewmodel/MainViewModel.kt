package com.example.testmobapp.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmobapp.data.api.RetrofitInstance
import com.example.testmobapp.data.model.AlbumsListResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    @SuppressLint("MutableCollectionMutableState")
    var albumList = mutableStateOf<AlbumsListResponse?>(null)
//    var albumList = mutableStateListOf<AlbumsListResponse?>(null)

    val api = RetrofitInstance.api

    fun getAlbumList() {
        viewModelScope.launch {
            delay(2000)
            albumList.value = (api.getAlbumList().body())

        }
    }

    init {
        getAlbumList()
    }

}