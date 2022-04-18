package com.example.movieapplication.data.single_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapplication.data.data.data.repository.NetworkState
import com.example.movieapplication.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieDetailsRepository: MovieDetailsRepository, movieId:Int): ViewModel(){
    private val compositeDisposable=CompositeDisposable()

    val movieDetails : LiveData<MovieDetails>by lazy {
        movieDetailsRepository.fetchMovieDetails(compositeDisposable,movieId)
    }
    val movieDetailsNetworkState : LiveData<NetworkState>by lazy {
        movieDetailsRepository.fetchMovieDetailsNetworkState()
    }
    override fun onCleared(){
        super.onCleared()
        compositeDisposable.dispose()
    }
}