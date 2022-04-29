package com.plcoding.calorytracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //DO NOT FORGET TO ADD TO THE MANIFEST - only needs to go in the app module mani :)
class CaloryTrackerApp: Application() { //allows hilt to have the application context

}