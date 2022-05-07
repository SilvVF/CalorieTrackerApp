package com.plcoding.tracker_data.mapper

import com.plcoding.tracker_data.remote.dto.Product
import com.plcoding.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

/**
 * maps other objects into a trackable food object
 * if any fields required are null it returns null
 */
fun Product.toTrackableFood(): TrackableFood? {

    val carbsPer100g = nutriments.carbohydrates100g.roundToInt()
    val proteinPer100g = nutriments.proteins100g.roundToInt()
    val fatPer100g = nutriments.fat100g.roundToInt()
    val caloriesPer100g = nutriments.energyKcal100g.roundToInt()

    return TrackableFood(
        name = this.productName ?: return null,
        imageUrl = imageFrontThumbUrl,
        carbsPer100g = carbsPer100g,
        caloriesPer100g = caloriesPer100g,
        fatPer100g = fatPer100g,
        proteinPer100g = proteinPer100g,
    )
}