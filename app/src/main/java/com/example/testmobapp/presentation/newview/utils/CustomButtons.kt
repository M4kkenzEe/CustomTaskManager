package com.example.testmobapp.presentation.newview.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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

@Composable
fun AddFAB(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        containerColor = Color.Black,
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
    buttonColor: Color = Color.Black,
    textColor: Color = Color.White
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = buttonColor
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .wrapContentHeight(Alignment.CenterVertically)
            .width(96.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

@Composable
fun ManageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Done!",
    buttonColor: Color = Color.Black,
    textColor: Color = Color.White,
    icon: Int
) {
    IconButton(
        onClick = { onClick() },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = buttonColor,
            contentColor = textColor,
        ),
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .width(80.dp),
    ) {
        Row {
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Text(text = text, fontSize = 10.sp, fontWeight = FontWeight.Normal)
        }
    }
}

@Composable
fun ManageBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "Done!",
    buttonColor: Color = Color.Black,
    textColor: Color = Color.White,
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
//            .size(200.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(id = icon), contentDescription = null)
            if (text != "") {
                Text(
                    text = text,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
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
        DoneButton()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            ManageBox(icon = R.drawable.ic_calendar_white)
            ManageBox(icon = R.drawable.ic_sandtimer_white)
            ManageBox(icon = R.drawable.ic_clock_white)
            ManageBox(icon = R.drawable.ic_flag_white)
            ManageBox(icon = R.drawable.ic_flag_white, text="")
        }
    }

}