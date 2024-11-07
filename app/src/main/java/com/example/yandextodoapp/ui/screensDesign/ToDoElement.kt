package com.example.yandextodoapp.ui.screensDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yandextodoapp.R
import com.example.yandextodoapp.data.TaskInfo
import com.example.yandextodoapp.ui.theme.YandexToDoAPPTheme
import com.example.yandextodoapp.viewModel.TaskViewModel

@Composable
fun ToDoElement(elementInfo: TaskInfo, onClick : (TaskInfo?) -> Unit){

    val textDecorationInfo = if (elementInfo.isCompleted) TextDecoration.LineThrough else null
    var isChecked by remember { mutableStateOf(elementInfo.isCompleted) }
    var textDecoration:TextDecoration?  by remember { mutableStateOf(textDecorationInfo) }
    var taskText by remember { mutableStateOf(elementInfo.taskName) }
    val taskViewModel: TaskViewModel = viewModel()

    Row(
        Modifier
            .padding(Dp(5.0F))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = isChecked,
            colors = CheckboxDefaults.colors(
                uncheckedColor = if (elementInfo.importance === "срочная" ) Color.Red else Color.Gray,
                checkedColor = Color.Green
            ),
            onCheckedChange = {
                    checked ->
                isChecked = checked
                textDecoration = if (checked) TextDecoration.LineThrough else null
                taskViewModel.setChangeState(elementInfo.id)
                taskViewModel.numDone = "Выполнено - ${taskViewModel.getDoneTasks()}"

        })
        Spacer(modifier = Modifier.width(8.dp))
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = taskText,
                textDecoration = textDecoration,
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight(300)
                )
            )
            Text(
                text = if (elementInfo.taskDescription.length > 30)
                        elementInfo.taskDescription.slice(0..25) + "..."
                    else
                        elementInfo.taskDescription,
                textDecoration = textDecoration,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(200)
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.icon_info),
            contentDescription = "icon_info",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(20.dp)
                .clickable {
                    onClick(elementInfo)
                }
        )
    }
}
