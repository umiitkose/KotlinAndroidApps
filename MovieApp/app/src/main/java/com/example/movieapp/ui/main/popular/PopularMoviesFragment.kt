package com.example.movieapp.ui.main.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.movieapp.R
import com.example.movieapp.common.BaseVMFragment
import com.example.movieapp.model.movie.MovieResults
import com.example.movieapp.ui.detail.DetailActivity
import com.example.movieapp.ui.main.MovieAdapter
import com.example.movieapp.util.Constans
import com.example.movieapp.util.gone
import com.example.movieapp.util.visible
import kotlinx.android.synthetic.main.fragment_popular_movies.*

class PopularMoviesFragment : BaseVMFragment<PopularMoviesViewModel>() , MovieAdapter.onMovieClickListener{


    private lateinit var adapter: MovieAdapter

    override fun getViewModel(): Class<PopularMoviesViewModel> = PopularMoviesViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MovieAdapter()
        popular_recyclerView.layoutManager= GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?

        adapter.setOnMovieClickListener(this)

        viewModel.getPopularMovies()?.observe(this, Observer {
            println("it 'i yazdırdım"+it.toString())
            adapter.submitList(it)
            popular_recyclerView.adapter=adapter
            popular_recyclerView.visible()
            popular_progressbar.gone()


        })
    }
    override fun onMovieClick(movieResults: MovieResults) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra(Constans.EXTRA_POPULAR_MOVIES, movieResults)
        startActivity(intent)

    }
}
