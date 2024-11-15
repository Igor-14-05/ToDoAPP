package com.example.yandextodoapp.viewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.yandextodoapp.data.TaskInfo

@SuppressLint("MutableCollectionMutableState")
class TaskViewModel : ViewModel() {

    var task: TaskInfo? by mutableStateOf(null)
    var numDone: String by mutableStateOf("Выполнено - ")

}