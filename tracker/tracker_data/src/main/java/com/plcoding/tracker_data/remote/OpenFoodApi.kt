package com.plcoding.tracker_data.remote

import com.plcoding.tracker_data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * interface for the open food api contains one api call to search for the food entered
 */
interface OpenFoodApi {
    //fields = only access certain fields from the api response
    @GET("cgi/search.pl?search_simple=1&json=1&action=process&fields=product_name,nutriments,image_front_thumb_url")
    suspend fun searchFood(
        @Query("search_terms") query: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): SearchDto //data class used to hold the json response

    companion object {
        const val BASE_URL = "https://us.openfoodfacts.org/"
    }
}