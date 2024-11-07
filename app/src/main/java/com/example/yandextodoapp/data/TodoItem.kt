package com.example.yandextodoapp.data

import kotlinx.serialization.Serializable

@Serializable
data class TaskInfo(
    val id: Int,
    var taskName : String,
    var taskDescription : String,
    var importance : String,
    var deadline : String = "отключено",
    var isCompleted : Boolean,
    val createAt : String? = null,
    val modifiedAt : String? = null
)

data class TasksInfo(
    val tasksInfo : List<TaskInfo>
)
