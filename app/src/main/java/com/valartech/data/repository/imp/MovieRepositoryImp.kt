package com.valartech.data.repository.imp

import com.valartech.base.BaseRepository
import com.valartech.data.db.Database
import com.valartech.data.model.moviedetailsmodel.MovieDetails
import com.valartech.data.model.moviemodel.Movie
import com.valartech.data.repository.abs.MovieDetailsRepository
import com.valartech.data.retrofit.APIClient
import com.valartech.global.helper.SharedPreferences
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImp @Inject
constructor(apiClient: APIClient, sharedPreferences: SharedPreferences, database: Database) :
    BaseRepository(apiClient, sharedPreferences, database),MovieDetailsRepository {
    override fun getMovieDetails(id:String,key:String): Single<MovieDetails> {
        return apiClient.getMovieDetails(id,key).map {
            it
        }
    }

    override fun getMovieList(name:String,key:String,page: Int): Single<Movie> {
        var search:String=" "
        if(name.equals("null")){
            search=" "
        }else{
            search=name
        }
        return apiClient.getMovieList(search,key,page).map {
            it
        }
    }


}