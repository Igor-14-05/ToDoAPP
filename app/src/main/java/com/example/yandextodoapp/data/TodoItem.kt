package com.example.yandextodoapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TaskInfo(
    val id: String,
    @SerializedName("text")
    var taskName : String,
//    var taskDescription : String,
    @SerializedName("importance")
    var importance : String,
    @SerializedName("deadline")
    var deadline : String = "отключено",
    @SerializedName("done")
    var isCompleted : Boolean,
    @SerializedName("color")
    val color : String? = null,
    @SerializedName("created_at")
    val createAt : String? = null,
    @SerializedName("changed_at")
    val modifiedAt : String? = null,
    val lastUpdate: String? = null

)

@Serializable
data class TasksInfo(
    @SerializedName("status")
    val status : String,
    @SerializedName("list")
    val tasksInfo : List<TaskInfo>,
    @SerializedName("revision")
    val revision : Int
)

@Serializable
data class aboutOneTask(
    @SerializedName("status")
    val status : String,
    @SerializedName("element")
    val element : TaskInfo,
    @SerializedName("revision")
    val revision : Int? = null
)
