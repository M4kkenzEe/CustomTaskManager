package com.example.testmobapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.testmobapp.presentation.view.references.TaskDescriptionTF
import com.example.testmobapp.presentation.view.references.TaskTitleTF
import com.example.testmobapp.presentation.viewmodel.TableViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddTaskScreen(viewModel: TableViewModel = koinViewModel()) {

    val keyboardController = LocalSoftwareKeyboardController.current
//    val keyboardShown = keyboardController?.

    var titleTfState by remember {
        mutableStateOf("")
    }

    var descTfState by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            ),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                TaskTitleTF(
                    value = titleTfState,
                    onValueChange = { titleTfState = it },
                    textHint = "Untitled",
                    textColor = Color.White,
                    cursorColor = Color.White,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    )
                )
            }

            item {
                TaskDescriptionTF(
                    value = descTfState,
                    onValueChange = { descTfState = it },
                    textHint = "Start writing...",
                    textColor = Color.White,
                    cursorColor = Color.White
                )
            }

            item { ButtonsTestRow() }
        }
    }
}

@Composable
fun ButtonsTestRow() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "ButtonTest1")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "ButtonTest1")
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "ButtonTest1")
        }
    }
}

@Composable
@Preview
fun AddTaskScreenPrev() {
    AddTaskScreen()
}