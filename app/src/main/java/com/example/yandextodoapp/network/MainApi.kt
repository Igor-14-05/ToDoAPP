package com.example.yandextodoapp.network

import com.example.yandextodoapp.data.TasksInfo
import com.example.yandextodoapp.data.aboutOneTask
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MainApi {
    @GET("todo/list/")
    suspend fun getListOfToDo(
        @Header("Authorization") authToken: String
    ): TasksInfo

    @GET("todo/list/{elementID}")
    suspend fun getToDoElement(
        @Path("elementID") id: Int,
        @Header("Authorization") authToken: String
    ): aboutOneTask

    @PUT("todo/list/{elementID}")
    suspend fun updateToDoElement(
        @Path("elementID") id: String,
        @Header("Authorization") authToken: String,
        @Header("X-Last-Known-Revision") revision: Int,
        @Body element : aboutOneTask
    ): aboutOneTask

    @DELETE("todo/list/{elementID}")
    suspend fun deleteToDoElement(
        @Path("elementID") id: String,
        @Header("Authorization") authToken: String,
        @Header("X-Last-Known-Revision") revision: Int
    ): aboutOneTask

    @POST("todo/list/")
    suspend fun putToDoElement(
        @Header("Authorization") authToken: String,
        @Header("X-Last-Known-Revision") revision: Int,
        @Body element : aboutOneTask
    ): aboutOneTask
}