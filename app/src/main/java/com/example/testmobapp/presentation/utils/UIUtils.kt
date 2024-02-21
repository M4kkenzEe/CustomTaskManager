package com.example.testmobapp.presentation.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context) {
    Toast.makeText(context, "Задача создана", Toast.LENGTH_SHORT).show()
}