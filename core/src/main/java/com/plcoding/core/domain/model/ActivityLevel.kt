package com.plcoding.core.domain.model

//gender used in the shared preferences
//needs a name param to store the data cant just store a sealed class
sealed class ActivityLevel(
    val name: String
) {
    object Low: ActivityLevel("low")
    object Medium: ActivityLevel("medium")
    object High: ActivityLevel("high")

    //take a String and return a gender object
    companion object {
        fun fromString(name: String) : ActivityLevel{
            return when (name){
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> Medium
            }
        }
    }
}
