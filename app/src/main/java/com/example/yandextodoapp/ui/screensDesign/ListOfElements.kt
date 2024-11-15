package com.example.yandextodoapp.ui.screensDesign

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yandextodoapp.app.App
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.viewModel.MainViewModel

@Composable
fun ListOfElements(onClick : (TaskInfo?) -> Unit, taskViewModel: MainViewModel) {
    val listToDoInfo by taskViewModel.listToDoInfo.observeAsState(emptyList())
    val errorInfo by taskViewModel.error.observeAsState("")
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(
                    Color.LightGray,
                    shape = RoundedCornerShape(
                        topStart = 10.dp, topEnd = 10.dp
                    ))
                .align(Alignment.TopCenter)
        ){
            if (listToDoInfo.isNotEmpty())
                items(listToDoInfo.size){index ->
                        ToDoElement(listToDoInfo[index], onClick)
                }
            else
                Toast.makeText(App.context, errorInfo, Toast.LENGTH_SHORT).show()
            }
        Button(
            onClick = {
                onClick(null)
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomCenter)
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Добавить новую задачу", fontSize = 20.sp)
        }
    }
}