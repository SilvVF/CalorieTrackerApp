package com.plcoding.tracker_data.remote.dto

/**
 * root object containing a list of products from open food api
 */
data class SearchDto(
    val products: List<Product>,
)