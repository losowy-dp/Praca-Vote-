package com.example.vote_01

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DyplomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}