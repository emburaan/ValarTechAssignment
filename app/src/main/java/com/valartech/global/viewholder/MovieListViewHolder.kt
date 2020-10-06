package com.valartech.global.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.valartech.R
import com.valartech.data.model.moviemodel.Search
import com.valartech.databinding.ItemMoviesBinding

import com.valartech.global.listener.OnItemClickedListener
import com.squareup.picasso.Picasso

class MovieListViewHolder(private val binding: ItemMoviesBinding, private val onItemClickedListener: OnItemClickedListener, private val picasso: Picasso):RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Search){
        binding.picasso=picasso
        binding.title=movie.Title
        binding.id=movie.imdbID
        binding.description="Released: "+movie.Year
        binding.onItemClickedListener = onItemClickedListener
        binding?.imageUrl = movie.Poster
        binding.placeHolder = AppCompatResources.getDrawable(binding.root.context, R.mipmap.ic_launcher)
    }
    companion object {
        fun create(parent: ViewGroup, onItemClickedListener: OnItemClickedListener, picasso: Picasso): MovieListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMoviesBinding.inflate(inflater, parent, false)
            return MovieListViewHolder(binding, onItemClickedListener,picasso)
        }
    }
}