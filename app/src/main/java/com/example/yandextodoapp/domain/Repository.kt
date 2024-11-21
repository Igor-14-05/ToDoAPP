package com.example.yandextodoapp.domain

import android.util.Log
import com.example.yandextodoapp.data.TasksInfo
import com.example.yandextodoapp.data.aboutOneTask
import com.example.yandextodoapp.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

object Repository {
    private val mainApi = ApiClient.mainApi()

    suspend fun getListInfo(): Flow<Result<TasksInfo>> {
        return flow {

            Log.d("Teeest 1", "I am here 10")

            val response = mainApi.getListOfToDo(
                authToken = "Bearer Thalion"
            )
            emit(Result.success(response))

        }.catch { e ->

            e.printStackTrace()

            emit(Result.failure(e))

        }.flowOn(Dispatchers.IO)
    }


    suspend fun deleteElementInfo(id : String,  revision : Int): Flow<Result<aboutOneTask>> {
        return flow {
            val response = mainApi.deleteToDoElement(
                authToken = "Bearer Thalion",
                id = id,
                revision = revision
            )
            emit(Result.success(response))
        }.catch { e ->
            e.printStackTrace()
            emit(Result.failure(e))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun putElementInfo(element : aboutOneTask, revision : Int): Flow<Result<aboutOneTask>> {
        return flow {
            val response = mainApi.putToDoElement(
                authToken = "Bearer Thalion",
                element = element,
                revision = revision
            )
            emit(Result.success(response))
        }.catch { e ->
            e.printStackTrace()
            emit(Result.failure(e))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateToDoElement(id : String,  element : aboutOneTask, revision : Int): Flow<Result<aboutOneTask>> {
        return flow {
            val response = mainApi.updateToDoElement(
                authToken = "Bearer Thalion",
                id = id,
                element = element,
                revision = revision
            )
            emit(Result.success(response))
        }.catch { e ->
            e.printStackTrace()
            emit(Result.failure(e))
        }.flowOn(Dispatchers.IO)
    }
}