package com.example.yandextodoapp.ui.screensDesign

import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yandextodoapp.R
import com.example.yandextodoapp.data.Importance
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.data.aboutOneTask
import com.example.yandextodoapp.viewModel.MainViewModel
import java.util.Calendar


@Composable
fun TaskEdit(taskViewModel: MainViewModel, taskInf: TaskInfo?, onClick : () -> Unit){

    val taskName = remember{mutableStateOf(" ")}
    val message = remember{mutableStateOf(" ")}
    val checkedState = remember { mutableStateOf(false) }
    val important = remember { mutableStateOf("низкая") }
    val selectedDate = remember { mutableStateOf("отключено") }
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

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
                text = stringResource(R.string.txt_save),
                color = Color.Blue,
                fontWeight = FontWeight(500),
                modifier = Modifier
                    .clickable{
                        if (taskInf != null) {
                            if (taskName.value == " ") {
                                Toast.makeText(context,
                                    "Поле не может быть пустым!!!",
                                    Toast.LENGTH_SHORT).show()
                            } else {
                                taskViewModel.updateElementToDo(
                                    aboutOneTask(
                                        element = TaskInfo(
                                            id = taskInf.id,
                                            taskName = taskName.value,
                                            isCompleted = false,
                                            createAt = taskInf.createAt,
                                            modifiedAt = System.currentTimeMillis(),
                                            lastUpdate = System.currentTimeMillis().toString()
                                        )
                                    ), taskInf.id, taskViewModel.revision)
                                onClick()
                            }

                        } else {
                            taskViewModel.putElementToDo(
                                aboutOneTask(
                                    element = TaskInfo(
                                        id = System.currentTimeMillis().toString(),
                                        taskName = taskName.value,
                                        isCompleted = false,
                                        createAt = System.currentTimeMillis(),
                                        modifiedAt = System.currentTimeMillis(),
                                        lastUpdate = System.currentTimeMillis().toString()
                                    )
                            ), taskViewModel.revision)
                            taskViewModel.getAllListToDo()
                            onClick()
                        }
                    }
            )
        }
        LaunchedEffect(taskInf) {
            taskInf?.let {
                if (it.taskName != taskName.value) {
                    taskName.value = it.taskName
                }
                if (it.taskName != taskName.value) {
                    message.value = it.taskName
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
            value = message.value,
            onValueChange = {
                newText -> message.value = newText
                            }
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(R.string.txt_importane) + important.value)
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
                                important.value = Importance.LOW.text
                            },
                    )
                    Text(text = "Нет",
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                            .clickable {
                                important.value = Importance.NORMAL.text
                            }
                    )
                    Text(text = "!!", color = Color.Red,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                            .clickable {
                                important.value = Importance.HIGH.text
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
            Text(text = stringResource(R.string.txt_do_by) +
                    if (taskInf?.deadline != null)
                        taskInf.deadline
                    else selectedDate.value
            )
            Switch(
                modifier = Modifier.height(20.dp),
                checked = if (taskInf?.deadline != null) true
                            else checkedState.value,
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

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false},
                title = { Text(text = stringResource(R.string.txt_confirm_action)) },
                text = { Text(stringResource(R.string.txt_ask_really_want)) },
                confirmButton = {
                    Button(
                        {
                            openDialog.value = false
                            taskViewModel.deleteElementToDo(taskInf?.id!!, taskViewModel.revision)
                            taskViewModel.getAllListToDo()
                            onClick()
                           }, border = BorderStroke(1.dp, Color.LightGray)) {
                        Text(stringResource(R.string.txt_delete), fontSize = 22.sp)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { openDialog.value = false },
                        border = BorderStroke(1.dp, Color.LightGray)) {
                        Text(stringResource(R.string.txt_cancel), fontSize = 22.sp)
                    }
                },
                containerColor = Color.DarkGray,
                titleContentColor = Color.LightGray,
                textContentColor = Color.LightGray,
                iconContentColor = Color.LightGray
            )
        }
        Button(
            onClick = {
                openDialog.value = true
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 15.dp)
        ) {
            Text(text = stringResource(R.string.txt_delete))
        }
    }
}