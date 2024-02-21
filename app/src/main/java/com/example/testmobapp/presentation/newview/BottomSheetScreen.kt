package com.example.testmobapp.presentation.newview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmobapp.R
import com.example.testmobapp.presentation.newview.references.DoneButton
import com.example.testmobapp.presentation.newview.references.ManageBox
import com.example.testmobapp.presentation.newview.references.TaskDescriptionTF
import com.example.testmobapp.presentation.newview.references.TaskTitleTF
import com.example.testmobapp.presentation.utils.showToast
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(
    showBottomSheet: Boolean,
    cancelAdding: () -> Unit = {},
    saveTask: (title: String, desc: String) -> Unit = { _, _ -> }
) {
    val context = LocalContext.current

    var titleState by remember {
        mutableStateOf("")
    }

    var descState by remember {
        mutableStateOf("")
    }

    val sheetState = rememberModalBottomSheetState()

    //Clear text fields after each finished action
    val clearFields = remember {
        {
            titleState = ""
            descState = ""
        }
    }

    LaunchedEffect(showBottomSheet) {
        if (!showBottomSheet) {
            clearFields()
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                clearFields()
                cancelAdding()
            },
            sheetState = sheetState,
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TaskTitleTF(
                    value = titleState,
                    onValueChange = { titleState = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ManageBox(
                        icon = R.drawable.ic_calendar_white,
                        text = LocalDate.now().toString()
                    )
                    ManageBox(icon = R.drawable.ic_sandtimer_white)
                    ManageBox(icon = R.drawable.ic_clock_white)
                    ManageBox(icon = R.drawable.ic_flag_white)
                }

                TaskDescriptionTF(
                    value = descState,
                    onValueChange = { descState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )

                DoneButton(onClick = {
                    saveTask(titleState, descState)
                    clearFields()
                    showToast(context)
                })
            }
        }
    }
}

@Composable
@Preview
fun BottomSheetScreenPrev() {
    BottomSheetScreen(true, {})
}