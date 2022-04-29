package com.plcoding.core.domain.model


//gender used in the shared preferences
//needs a name param to store the data cant just store a sealed class
sealed class GoalType(
    val name: String
) {
    object LoseWeight: GoalType("lose_weight")
    object KeepWeight: GoalType("keep_weight")
    object GainWeight: GoalType("gain_weight")

    //take a String and return object
    companion object {
        fun fromString(name: String) : GoalType{
            return when (name){
                "lose_weight" -> LoseWeight
                "keep_weight" -> KeepWeight
                "gain_weight" -> GainWeight
                else -> KeepWeight
            }
        }
    }
}
