package com.valartech.data.model.moviemodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
):Parcelable