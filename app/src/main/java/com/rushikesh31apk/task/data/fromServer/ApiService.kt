package com.rushikesh31apk.task.data.fromServer

import com.rushikesh31apk.task.data.fromServer.responce.TaskResponceItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("images/search")
    suspend fun getImages(
        @Query("limit") limit: Int = 100,
        @Query("page") page: Int = 10,
        @Query("order") order: String = "Desc"
    ): List<TaskResponceItem>

}