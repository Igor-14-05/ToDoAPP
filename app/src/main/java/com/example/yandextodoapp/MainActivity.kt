package com.example.yandextodoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.ui.screensDesign.MainPage
import com.example.yandextodoapp.ui.screensDesign.TaskEdit
import com.example.yandextodoapp.viewModel.TaskViewModel
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val taskViewModel: TaskViewModel = viewModel()
            val navController : NavHostController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "main_page"){
                composable("main_page"){
                    MainPage { taskInf ->
                        taskViewModel.task = taskInf
                        navController.navigate("edit_page")
                    }
                }
                composable("edit_page"){
                    TaskEdit (taskViewModel.task) {
                        navController.navigate("main_page")
                    }
                }
            }
        }
    }
}
