/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.*
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.*
import com.example.androiddevchallenge.ui.theme.MyTheme

// 3.. 2.. 1.. Lift off! Create a single screen working countdown timer
// and learn all about state in Compose.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(timerViewModel: TimerViewModel = viewModel()) {
    val timeLeft: String by timerViewModel.timer.observeAsState("Ready")
    val timeSelected: String by timerViewModel.timeSelected.observeAsState("")
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = timeSelected, modifier = Modifier.align(Alignment.CenterHorizontally))
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                TimeSelectionButton(timerViewModel, "5 Seconds", 5000)
                TimeSelectionButton(timerViewModel, "30 Seconds", 30000)
                TimeSelectionButton(timerViewModel, "1 Minute", 60000)
            }
            Button(onClick = {timerViewModel.startTimer()},
                colors = ButtonDefaults.textButtonColors(backgroundColor = Color.LightGray),
                modifier = Modifier.padding(8.dp)) {
                Text(text = "Start")
            }
            Text(text = timeLeft,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(24.dp),
                fontSize = 90.sp)
        }
    }
}

@Composable
private fun TimeSelectionButton(timerViewModel: TimerViewModel, label: String, timeInMillis: Long) {
    Button(
        onClick = { timerViewModel.setTimerInMillis(timeInMillis) },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.LightGray
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = label)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
