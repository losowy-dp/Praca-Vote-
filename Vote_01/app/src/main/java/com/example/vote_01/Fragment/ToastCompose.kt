package com.example.vote_01.Fragment

import android.content.Context
import android.widget.Toast

import androidx.compose.ui.platform.LocalContext


fun ToastComp(context: Context, messege:String) {
    Toast
        .makeText(context, messege, Toast.LENGTH_SHORT)
        .show()
}