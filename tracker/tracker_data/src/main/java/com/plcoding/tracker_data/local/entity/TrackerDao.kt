package com.plcoding.tracker_data.local.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    //when there is already a value with same data it will be replaced
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)


    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    //returns a flow that is triggered every time there is a change to the database
    @Query(
        """
            SELECT * 
            FROM trackedfoodentity
            WHERE dayOfMonth = :day AND month = :month AND year = :year
        """
    )
    fun getFoodsForDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>

}