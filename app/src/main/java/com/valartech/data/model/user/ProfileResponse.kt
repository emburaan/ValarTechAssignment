package com.valartech.data.model.user

import com.squareup.moshi.Json

data class ProfileResponse(
    @Json(name = "data")
    val data: User
)
