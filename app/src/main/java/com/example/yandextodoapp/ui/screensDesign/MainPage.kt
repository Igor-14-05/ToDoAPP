package com.example.yandextodoapp.ui.screensDesign

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.viewModel.MainViewModel


@Composable
fun MainPage(taskViewModel: MainViewModel, onClick: (TaskInfo?) -> Unit) {
    Column {
        TopOfMainPage()
        ListOfElements(onClick, taskViewModel)
    }
}