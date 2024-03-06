package com.example.testmobapp.presentation.newview.references

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.testmobapp.presentation.model.PriorityTag
import com.example.testmobapp.presentation.ui.theme.PriorityGreen
import com.example.testmobapp.presentation.ui.theme.PriorityOrange
import com.example.testmobapp.presentation.ui.theme.PriorityRed

@Composable
fun PriorityCircle(modifier: Modifier = Modifier, taskPriority: PriorityTag) {

    val priorityColor = when (taskPriority) {
        PriorityTag.RED -> PriorityRed
        PriorityTag.ORANGE -> PriorityOrange
        PriorityTag.GREEN -> PriorityGreen
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(8.dp)
            .background(priorityColor)
    )
}