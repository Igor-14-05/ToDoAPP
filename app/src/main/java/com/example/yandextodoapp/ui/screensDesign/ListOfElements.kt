package com.example.yandextodoapp.ui.screensDesign

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.data.listOfElement
import com.example.yandextodoapp.viewModel.TaskViewModel

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ListOfElements(onClick : (TaskInfo?) -> Unit) {
    val taskViewModel: TaskViewModel = viewModel()
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
                items(taskViewModel.SizeUpdate.intValue){lang ->
                    if (taskViewModel.SizeUpdate.intValue == listOfElement.size)
                        ToDoElement(taskViewModel.visibleAllItems[lang], onClick)
                    else
                        ToDoElement(taskViewModel.visibleItems[lang], onClick)
                }
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