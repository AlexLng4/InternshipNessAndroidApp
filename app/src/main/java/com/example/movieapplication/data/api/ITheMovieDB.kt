package com.example.movieapplication.data.api

import com.example.movieapplication.data.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface ITheMovieDB {
    ///movie/550?api_key=
    ////https://api.themoviedb.org/3/movie/popular?api_key=43d99e9835a01d2ab7a821edc63c0ec1

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MovieDetails>
}