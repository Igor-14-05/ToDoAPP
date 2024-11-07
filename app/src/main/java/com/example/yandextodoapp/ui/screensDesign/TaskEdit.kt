package com.example.yandextodoapp.ui.screensDesign

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yandextodoapp.R
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.viewModel.TaskViewModel
import java.util.Calendar


@Composable
fun TaskEdit(taskInf: TaskInfo?, onClick : () -> Unit){

    val taskName = remember{mutableStateOf(" ")}
    val message = remember{mutableStateOf(" ")}
    val checkedState = remember { mutableStateOf(false) }
    val important = remember { mutableStateOf("низкая") }
    val taskViewModel: TaskViewModel = viewModel()
    var selectedDate = remember { mutableStateOf("отключено") }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_close),
                contentDescription = "icon_info",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        onClick()
                    },
            )
            Text(
                text = "СОХРАНИТЬ",
                color = Color.Blue,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .clickable{
                        if (taskInf == null) {
                            taskViewModel.setElement(taskName.value, message.value, important.value, selectedDate.value)
                        } else {
                            taskViewModel.updateElement(taskInf.id, taskName.value, message.value, important.value, selectedDate.value)
                        }
                        onClick()
                    }

            )
        }

        LaunchedEffect(taskInf) {
            taskInf?.let {
                if (it.taskName != taskName.value) {
                    taskName.value = it.taskName
                }
            }
        }

        TextField(
            readOnly = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            value = taskName.value,
            onValueChange = {
                    newText -> taskName.value = newText
            }
        )
        TextField(
            readOnly = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            value = if (taskInf != null && taskInf.taskDescription !=  message.value) taskInf.taskDescription else message.value,
            onValueChange = {
                newText -> taskName.value = newText
                            }
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Важность - ${important.value}")
            Box (
                modifier = Modifier
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(7.dp)
                    )
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_arrow),
                        contentDescription = "icon_info",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(horizontal = 7.dp, vertical = 2.dp)
                            .clickable {
                                important.value = "низкая"
                            },
                    )
                    Text(text = "Нет",
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                            .clickable{
                                important.value = "обычная"
                            }
                    )
                    Text(text = "!!", color = Color.Red,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                            .clickable{
                                important.value = "срочная"
                        }
                    )
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Сделать до - ${selectedDate.value}")
            Switch(
                modifier = Modifier.height(20.dp),
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    if (it) {
                        val calendar = Calendar.getInstance()
                        val year = calendar.get(Calendar.YEAR)
                        val month = calendar.get(Calendar.MONTH)
                        val day = calendar.get(Calendar.DAY_OF_MONTH)
                        DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                            selectedDate.value = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                        }, year, month, day).show()
                    } else {
                        selectedDate.value = "отключено"
                    }
                }
            )
        }
        Button(
            onClick = {
                if (taskInf != null)
                    taskViewModel.deleteElementByID(taskInf.id)
                onClick()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            Text(text = "Удалить")
        }
    }
}