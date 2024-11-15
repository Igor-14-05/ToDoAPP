package com.example.yandextodoapp.ui.screensDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.yandextodoapp.R
import com.example.yandextodoapp.ui.theme.YandexToDoAPPTheme
import com.example.yandextodoapp.viewModel.TaskViewModel

@Composable
fun TopOfMainPage() {
    val taskViewModel: TaskViewModel = viewModel()
    val picId = remember{ mutableIntStateOf( R.drawable.icon_open_eye ) }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(200.dp)
        ){
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Мои дела!",
                style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight(500)
                ),
                modifier = Modifier.padding(horizontal = 40.dp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = taskViewModel.numDone,
                    style = TextStyle(
                        fontSize = 25.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight(300)
                    ),
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
                Image(
                    painter = painterResource(id = picId.intValue),
                    contentDescription = "icon_eye",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .size(35.dp)
                        .clickable{
                            if (picId.intValue == R.drawable.icon_close_eye) {
                                picId.intValue = R.drawable.icon_open_eye

                            } else{
                                picId.intValue = R.drawable.icon_close_eye

                            }

                        }
                )
            }
        }
    }
}