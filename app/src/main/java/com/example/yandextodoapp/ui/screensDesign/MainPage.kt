package com.example.yandextodoapp.ui.screensDesign

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.ui.theme.YandexToDoAPPTheme


@Composable
fun MainPage(onClick : (TaskInfo?) -> Unit) {
    Column {
        TopOfMainPage()
        ListOfElements(onClick)
    }
}