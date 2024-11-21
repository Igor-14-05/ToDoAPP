package com.example.yandextodoapp.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class TaskInfo(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    var taskName : String,
    @SerializedName("importance")
    var importance : String = "low",
    @SerializedName("deadline")
    var deadline : Int? = null,
    @SerializedName("done")
    var isCompleted : Boolean,
    @SerializedName("color")
    val color : String? = null,
    @SerializedName("created_at")
    val createAt : Long? = null,
    @SerializedName("changed_at")
    val modifiedAt : Long? = null,
    @SerializedName("last_updated_by")
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
    val status : String? = null,
    @SerializedName("element")
    val element : TaskInfo,
    @SerializedName("revision")
    val revision : Int? = null
)