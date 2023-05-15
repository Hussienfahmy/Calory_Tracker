package com.h_fahmy.tracker_data.remote.dto

import com.squareup.moshi.Json

data class Product(
    @field:Json(name = "image_front_thumb_url")
    val imageFrontThumbUrl: String?,
    val nutriment: Nutriment,
    @field:Json(name = "product_name")
    val productName: String?
)
