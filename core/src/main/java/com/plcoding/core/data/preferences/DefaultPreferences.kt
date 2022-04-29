package com.plcoding.core.data.preferences

import android.content.SharedPreferences
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_ACTIVITY_LEVEL
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_AGE
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_CARB_RATIO
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_FAT_RATIO
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_GENDER
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_GOAL_TYPE
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_HEIGHT
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_PROTEIN_RATIO
import com.plcoding.core.domain.preferences.Preferences.Companion.KEY_WEIGHT

//class defines the implementation of storing the preferences - Using shared preferences
//contains the function to get the user info object that stores all of the shared preferences

class DefaultPreferences(
    private val sharedPref: SharedPreferences
): Preferences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit()           //name param can be retrieved and stored using string val
            .putString(KEY_GENDER,gender.name)  //add the val to the shred pref Map K, V pairs
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(KEY_HEIGHT, height)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit()
            .putString(KEY_ACTIVITY_LEVEL, level.name)
            .apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPref.edit()
            .putString(KEY_GOAL_TYPE, goalType.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_CARB_RATIO, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(KEY_FAT_RATIO, ratio)
            .apply()
    }
/**
return: returns a user info object containing all of the set shared preferences combined
 */
    override fun loadUserInfo(): UserInfo {
        //load all of the data from shared preferences and combine
        //it to a single user object
        val gender = sharedPref.getString(KEY_GENDER, null)
        val age = sharedPref.getInt(KEY_AGE, -1) //-1 = default case
        val weight = sharedPref.getFloat(KEY_WEIGHT, -1F)
        val height = sharedPref.getInt(KEY_HEIGHT, -1)
        val activityLevel = sharedPref.getString(KEY_ACTIVITY_LEVEL, null)
        val goalType = sharedPref.getString(KEY_GOAL_TYPE, null)
        val carbRatio = sharedPref.getFloat(KEY_CARB_RATIO, -1F)
        val proteinRatio = sharedPref.getFloat(KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPref.getFloat(KEY_FAT_RATIO, -1f)

        return UserInfo(
            gender = Gender.fromString(gender ?: "female"), //return the object from the string value
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevel ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keep"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio,
        )
    }
}