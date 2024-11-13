package com.example.yandextodoapp.viewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.data.listOfElement

@SuppressLint("MutableCollectionMutableState")
class TaskViewModel : ViewModel() {

    var task: TaskInfo? by mutableStateOf(null)
    var numDone: String by mutableStateOf("Выполнено - ${getCountDoneTasks()}")

    fun getCountDoneTasks() : Int {
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
        listOfElement[id].taskName = message
        listOfElement[id].importance = important
        listOfElement[id].isCompleted = false
        listOfElement[id].deadline = deadline
    }

    fun deleteElementByID(id: Int){
        for (i in listOfElement.size - 1 downTo 0){
            if (listOfElement[i].id.toInt() == id)
                listOfElement.removeAt(i)
        }
    }

    fun setElement(name : String, message : String, important : String, deadline : String) {
        listOfElement.add(TaskInfo(id = listOfElement.size.toString(),
            taskName = name,
            importance = important, isCompleted = false,
            deadline = deadline, color = null, lastUpdate = null))
    }

    var visibleItems: SnapshotStateList<TaskInfo> = mutableStateListOf()
    var visibleAllItems = mutableStateListOf(*listOfElement.toTypedArray())
    var SizeUpdate = mutableIntStateOf(listOfElement.size)

    fun setChangeState(id: Int){
        listOfElement[id].isCompleted = !listOfElement[id].isCompleted
    }

    private fun getDoneTasks() {
        visibleItems.clear()
        for (i in listOfElement.size - 1 downTo 0){
            if (listOfElement[i].isCompleted)
                visibleItems.add(listOfElement[i])
        }
    }

    fun toggleAll(showAll: Boolean) {
        if (showAll) {
            SizeUpdate.intValue = visibleAllItems.size
        } else {
            getDoneTasks()
            SizeUpdate.intValue = visibleItems.size
        }
    }

}