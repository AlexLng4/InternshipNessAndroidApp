package com.example.movieapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapplication.data.api.ITheMovieDB
import com.example.movieapplication.data.data.data.repository.NetworkState
import com.example.movieapplication.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailsNtDataSource (private val apiService: ITheMovieDB, private val CompositeDisposable: CompositeDisposable){

    private val _networkState=MutableLiveData<NetworkState>()
    val networkState:LiveData<NetworkState>
        get() = _networkState
    private val _downloadedMovieDetailsResponse=MutableLiveData<MovieDetails>()
    val downloadedMovieResponse:LiveData<MovieDetails>
        get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId:Int) {
        _networkState.postValue(NetworkState.LOADING)
        try {
            CompositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        _downloadedMovieDetailsResponse.postValue(it)
                        _networkState.postValue(NetworkState.LOADED)
                    },{
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDetailsDataSource", it.message.toString())
                    })
            )
        }catch (e: Exception){
            Log.e("MovieDetailsDataSource", e.message.toString())
        }
    }


}