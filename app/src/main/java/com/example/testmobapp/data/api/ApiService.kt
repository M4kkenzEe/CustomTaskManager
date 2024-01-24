package com.example.testmobapp.data.api

import com.example.testmobapp.data.model.AlbumsListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    suspend fun getAlbumList(): Response<AlbumsListResponse>
}