package com.example.yandextodoapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.data.TasksInfo
import com.example.yandextodoapp.data.aboutOneTask
import com.example.yandextodoapp.domain.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel()  {

    private val _listToDoInfo = MutableLiveData<TasksInfo>()
    val listToDoInfo: LiveData<List<TaskInfo>>
        get() = _listToDoInfo.map { it.tasksInfo }

    private var _revision: Int = 0
    val revision: Int get() = _revision

    private val _elementToDoInfo = MutableLiveData<aboutOneTask>()
    val elementToDoInfo: LiveData<aboutOneTask> get() = _elementToDoInfo


    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error.map { it.toString() }

    fun getAllListToDo(){
        viewModelScope.launch {
            Repository.getListInfo().collect { result ->

                result.onSuccess {
                    _listToDoInfo.value = it
                    _revision = it.revision
                    Log.d("dsd", "fdfsd")
                }

                result.onFailure {
                    _error.value = it.message.toString()
                    _revision = 0
                    Log.d("dsd", it.message.toString())
                }
            }
        }
    }


    fun putElementToDo(element: aboutOneTask, revision : Int){
        viewModelScope.launch {
            Repository.putElementInfo(element, revision).collect { result ->

                result.onSuccess {
                    _elementToDoInfo.value = it
                }

                result.onFailure {
                    _error.value = it.message.toString()
                }
            }
        }
    }

    fun deleteElementToDo(id: String, revision : Int){
        viewModelScope.launch {
            Repository.deleteElementInfo(id, revision).collect { result ->
                result.onSuccess {
                    _elementToDoInfo.value = it
                }
                result.onFailure {
                    _error.value = it.message.toString()
                }
            }
        }
    }


    fun updateElementToDo(element: aboutOneTask, id: String, revision : Int){
        viewModelScope.launch {
            Repository.updateToDoElement(id, element, revision).collect { result ->

                result.onSuccess {
                    _elementToDoInfo.value = it
                }

                result.onFailure {
                    _error.value = it.message.toString()
                }
            }
        }
    }
}