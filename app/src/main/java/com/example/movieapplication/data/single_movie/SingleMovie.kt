package com.example.movieapplication.data.single_movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.data.api.ClientTheMovieDB
import com.example.movieapplication.data.api.ITheMovieDB
import com.example.movieapplication.data.api.POSTER_BASE_URL
import com.example.movieapplication.data.data.data.repository.NetworkState
import com.example.movieapplication.data.vo.MovieDetails
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*


class SingleMovie : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var singleRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        val movieId:Int= intent.getIntExtra("id", 1)
        val apiService: ITheMovieDB = ClientTheMovieDB.getClient()
        singleRepository= MovieDetailsRepository((apiService))
        viewModel=getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })
        viewModel.movieDetailsNetworkState.observe(this, Observer
        { progress_bar.visibility=if(it== NetworkState.LOADING) View.VISIBLE else View.GONE
            text_error.visibility=if(it==NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    fun bindUI(it: MovieDetails?) {
        if (it != null) {
            single_movie_title.text=it.title
            single_movie_subtitle.text=it.tagline
            single_movie_release_date.text=it.releaseDate
            single_movie_runtime.text=it.runtime.toString()
            single_movie_rating.text=it.rating.toString()
            single_movie_overview.text=it.overview

            val formatCurrency=NumberFormat.getCurrencyInstance(Locale.US)
            single_movie_budget.text=formatCurrency.format(it.budget)
            single_movie_revenue.text=formatCurrency.format(it.revenue)

            val moviePosterURL= POSTER_BASE_URL+it.posterPath
            Glide.with(this)
                .load(moviePosterURL)
                .into(single_movie_image)
        }

    }

    private fun getViewModel(movieId: Int): SingleMovieViewModel {
        return ViewModelProviders.of(this,object :ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return  SingleMovieViewModel(singleRepository,movieId)as T
            }
        })[SingleMovieViewModel::class.java]

    }
}