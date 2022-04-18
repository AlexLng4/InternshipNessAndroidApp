package com.example.movieapplication.data.data.data.repository
enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}
class NetworkState (val status: Status, val msg: String){

    companion object{
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        init {
            LOADED = NetworkState(Status.SUCCESS,"Operation have success")
            LOADING = NetworkState(Status.RUNNING,"Still in progress")
            ERROR = NetworkState(Status.FAILED, "Ups! Something went wrong")
        }
    }
}