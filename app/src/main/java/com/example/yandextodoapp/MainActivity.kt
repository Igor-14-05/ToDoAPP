package com.example.yandextodoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yandextodoapp.ui.screensDesign.MainPage
import com.example.yandextodoapp.ui.screensDesign.TaskEdit
import com.example.yandextodoapp.viewModel.MainViewModel
import com.example.yandextodoapp.viewModel.TaskViewModel



class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
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

        viewModel.getElementToDo(1)
        viewModel.listToDoInfo.observe(this){
            Log.d("HELLLLO", it.toString())
        }

        viewModel.elementToDoInfo.observe(this){
            Log.d("HELLLLO", it.toString())
        }

        viewModel.error.observe(this){
            Log.d("HELLLLO", it.toString())
        }
    }
}
