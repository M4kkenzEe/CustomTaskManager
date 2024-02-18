package com.example.testmobapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.testmobapp.presentation.navigation.MainScreen
import com.example.testmobapp.ui.theme.TestMobAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TestMobAppTheme {

                MainScreen()

//                val scheduler = AndroidAlarmScheduler(this)
//                var alarmItem: AlarmItem? = null
//                var secondsText by remember {
//                    mutableStateOf("")
//                }
//                var message by remember {
//                    mutableStateOf("")
//                }
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    OutlinedTextField(
//                        value = secondsText,
//                        onValueChange = { secondsText = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = {
//                            Text(text = "Trigger alarm in seconds")
//                        }
//                    )
//                    OutlinedTextField(
//                        value = message,
//                        onValueChange = { message = it },
//                        modifier = Modifier.fillMaxWidth(),
//                        placeholder = {
//                            Text(text = "Message")
//                        }
//                    )
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Button(onClick = {
//                            alarmItem = AlarmItem(
//                                time = LocalDateTime.now()
//                                    .plusSeconds(secondsText.toLong()),
//                                message = message
//                            )
//                            alarmItem?.let(scheduler::schedule)
//                            secondsText = ""
//                            message = ""
//                        }) {
//                            Text(text = "Schedule")
//                        }
//                        Button(onClick = {
//                            alarmItem?.let(scheduler::cancel)
//                        }) {
//                            Text(text = "Cancel")
//                        }
//                    }
//                }

            }
        }
    }
}
