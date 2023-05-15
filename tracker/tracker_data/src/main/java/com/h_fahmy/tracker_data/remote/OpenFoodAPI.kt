package com.h_fahmy.tracker_data.remote

import com.h_fahmy.tracker_data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFoodAPI {

    @GET("cgi/search.pl?search_simple=1&action=process&json=1&fields=product_name,nutriment,image_front_thumb_url")
    suspend fun search(
        @Query("search_terms") searchTerms: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): SearchDto

    companion object {
        const val BASE_URL = "https://us.openfoodfacts.org/"
    }
}