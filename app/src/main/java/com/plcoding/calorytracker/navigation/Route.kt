package com.plcoding.calorytracker.navigation

//contains the routes that will be used when navigation from one composable to another
// instead of hard coding strings use the object to prevent typos

object Route {
    //features needed in the on-boarding screen
    const val WELCOME = "welcome"
    const val AGE = "age"
    const val GENDER = "gender"
    const val HEIGHT = "height"
    const val WEIGHT = "weight"
    const val NUTRIENT_GOAL = "nutrient_goal"
    const val ACTIVITY = "activity"
    const val GOAL = "goal"

    //routes to the tracker features
    const val TRACKER_OVERVIEW = "tracker_overview"
    const val SEARCH = "search"
}