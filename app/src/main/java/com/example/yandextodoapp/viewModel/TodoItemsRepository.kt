package com.example.yandextodoapp.viewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.data.listOfElement

@SuppressLint("MutableCollectionMutableState")
class TaskViewModel : ViewModel() {

    var task: TaskInfo? by mutableStateOf(null)
    var numDone: String by mutableStateOf("Выполнено - ${getDoneTasks()}")

    fun getDoneTasks() : Int {
        var rez = 0
        for (i in listOfElement.size - 1 downTo 0){
            if (listOfElement[i].isCompleted)
                rez += 1
        }
        return rez
    }

    fun updateElement(id: Int, name : String, message : String, important : String,
                      deadline : String){
        listOfElement[id].taskName = name
        listOfElement[id].taskDescription = message
        listOfElement[id].importance = important
        listOfElement[id].isCompleted = false
        listOfElement[id].deadline = deadline
    }

    fun deleteElementByID(id: Int){
        for (i in listOfElement.size - 1 downTo 0){
            if (listOfElement[i].id == id)
                listOfElement.removeAt(i)
        }
    }

    fun setElement(name : String, message : String, important : String, deadline : String) {
        listOfElement.add(TaskInfo(id = listOfElement.size,
            taskName = name, taskDescription = message,
            importance = important, isCompleted = false,
            deadline = deadline))
    }

    fun setChangeState(id: Int){
        listOfElement[id].isCompleted = !listOfElement[id].isCompleted
    }

}