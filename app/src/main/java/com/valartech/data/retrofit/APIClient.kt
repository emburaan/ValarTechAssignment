package com.valartech.data.retrofit

import com.valartech.data.model.moviedetailsmodel.MovieDetails
import com.valartech.data.model.moviemodel.Movie
import com.valartech.data.model.news.News
import com.valartech.data.model.user.ProfileResponse
import io.reactivex.Single
import retrofit2.http.*


/**
 * Created on 2/2/18.
 */

interface APIClient {

    @FormUrlEncoded
    @POST("medamine/paginate/signin.json")
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Single<ProfileResponse>

    @FormUrlEncoded
    @POST("medamine/paginate/signin.json")
    fun signUpAndCache(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("opt_in") option: String
    ): Single<ProfileResponse>


    @GET("medamine/paginate/paginate.php")
    fun getNews(@Query("p") page: Int, @Query("pageSize") pageSize: Int): Single<List<News>>

    @GET(" ")
    fun getMovieDetails(@Query("i") id:String,@Query("apikey")  key: String): Single<MovieDetails>

    @GET(" ")
    fun getMovieList(@Query("s")  searchname:String,@Query("apikey")  key: String,@Query("page")  page: Int ): Single<Movie>

}
