package com.example.testmobapp.presentation.newview.references

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testmobapp.R
import com.example.testmobapp.presentation.model.PriorityTag
import com.example.testmobapp.presentation.ui.theme.Black34
import com.example.testmobapp.presentation.ui.theme.Coffee

@Composable
fun AddFAB(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = Coffee,
        contentColor = Color.White,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier.width(72.dp)
        )
    }
}

@Composable
fun DoneButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Done!",
    buttonColor: Color = Color.White,
    textColor: Color = Color.Black
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = buttonColor
        ),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
            .wrapContentHeight(Alignment.CenterVertically)
            .width(96.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

@Composable
fun ManageBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "",
    buttonColor: Color = Coffee,
    textColor: Color = Black34,
    icon: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(buttonColor)
            .clickable { onClick() }
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .height(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon in (0..2)) {
                val taskPriority = when (icon) {
                    1 -> PriorityTag.ORANGE
                    2 -> PriorityTag.RED
                    else -> PriorityTag.GREEN
                }
                PriorityCircle(taskPriority = taskPriority)
            } else {
                Image(painter = painterResource(id = icon), contentDescription = null)
            }

            if (text != "") {
                Text(
                    text = text,
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = textColor,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun ButtonsPrev() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(50.dp)) {
        AddFAB()
        DoneButton(text = "Done button")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ManageBox(icon = R.drawable.ic_calendar_white)
            ManageBox(icon = R.drawable.ic_sandtimer_white)
            ManageBox(icon = R.drawable.ic_clock_white)
            ManageBox(icon = R.drawable.ic_flag_white)
            ManageBox(icon = R.drawable.ic_flag_white, text = "")
        }
    }

}