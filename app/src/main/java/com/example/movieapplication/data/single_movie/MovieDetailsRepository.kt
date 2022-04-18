package com.example.movieapplication.data.single_movie

import androidx.lifecycle.LiveData
import com.example.movieapplication.data.api.ITheMovieDB
import com.example.movieapplication.data.data.data.repository.NetworkState
import com.example.movieapplication.data.repository.MovieDetailsNtDataSource
import com.example.movieapplication.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(var apiService: ITheMovieDB) {
    lateinit var movieDetailsNtDataSource: MovieDetailsNtDataSource
    fun fetchMovieDetails(compositeDisposable: CompositeDisposable,movieId:Int):LiveData<MovieDetails>{
        movieDetailsNtDataSource= MovieDetailsNtDataSource(apiService,compositeDisposable)
        movieDetailsNtDataSource.fetchMovieDetails(movieId)
        return movieDetailsNtDataSource.downloadedMovieResponse
    }
    fun fetchMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNtDataSource.networkState
    }
}