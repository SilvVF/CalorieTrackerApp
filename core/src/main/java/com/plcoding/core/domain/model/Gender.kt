package com.plcoding.core.domain.model

//gender used in the shared preferences
//needs a name param to store the data cant just store a sealed class
sealed class Gender(
    val name: String  //used to store the Gender as a string in the shared preferences
) {
    object Male: Gender("male")
    object Female: Gender("female")

    //take a String and return a gender object
    companion object {
        fun fromString(name: String) : Gender{
            return when (name){
                "male" -> Male
                "female" -> Female
                else -> Female
            }
        }
    }
}
