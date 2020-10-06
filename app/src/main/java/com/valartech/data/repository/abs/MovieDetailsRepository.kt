package com.valartech.data.repository.abs

import com.valartech.data.model.moviedetailsmodel.MovieDetails
import com.valartech.data.model.moviemodel.Movie
import io.reactivex.Single

interface MovieDetailsRepository {
   fun getMovieDetails(id:String,api:String):Single<MovieDetails>

   fun getMovieList(name:String, loadSize: String,page: Int): Single<Movie>
}