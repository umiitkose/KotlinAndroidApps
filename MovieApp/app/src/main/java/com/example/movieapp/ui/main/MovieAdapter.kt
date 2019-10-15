package com.example.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.model.movie.MovieResults

class MovieAdapter: ListAdapter<MovieResults,MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onMovieClickListener: onMovieClickListener

    fun setOnMovieClickListener(onMovieClickListener: onMovieClickListener){
        this.onMovieClickListener = onMovieClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.create(
        LayoutInflater.from(parent.context),parent,onMovieClickListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(getItem(position))


    class ViewHolder(val binding: ItemMovieBinding, onMovieClickListener: onMovieClickListener): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                onMovieClickListener.onMovieClick(binding.movie!!)
            }
        }

        companion object{

            fun create(inflater: LayoutInflater, parent: ViewGroup, onMovieClickListener: onMovieClickListener): ViewHolder{
                val itemMovieBinding = ItemMovieBinding.inflate(inflater,parent, false)

                return ViewHolder(itemMovieBinding,onMovieClickListener)
            }

        }

        fun bind(movieResults: MovieResults){
            binding.movie= movieResults
            binding.executePendingBindings()
        }

    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResults>(){
            override fun areItemsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieResults, newItem: MovieResults): Boolean  =
                oldItem.original_title== newItem.original_title

        }
    }

    interface onMovieClickListener{
        fun onMovieClick(movieResults: MovieResults)
    }
}