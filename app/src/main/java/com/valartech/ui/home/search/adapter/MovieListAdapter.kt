package com.valartech.ui.home.search.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.valartech.data.model.moviemodel.Search
import com.valartech.global.enumeration.State
import com.valartech.global.listener.PaginationStateListener
import com.valartech.global.viewholder.ListFooterViewHolder
import com.valartech.global.viewholder.MovieListViewHolder
import com.valartech.ui.home.search.SearchViewModel
import com.squareup.picasso.Picasso

private const val DATA_VIEW_TYPE = 1
private const val FOOTER_VIEW_TYPE = 2


class MovieListAdapter(private val picasso: Picasso) : PagedListAdapter<Search, RecyclerView.ViewHolder>(
    moviesDiffCallback), PaginationStateListener {

    private var state = State.LOADING

    lateinit var viewModel: SearchViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) {
            MovieListViewHolder.create(parent, viewModel,picasso)
        } else {
            ListFooterViewHolder.create(parent, viewModel)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            (holder as MovieListViewHolder).bind(getItem(position)!!)
        } else {
            (holder as ListFooterViewHolder).bind(state)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    companion object {
        val moviesDiffCallback = object : DiffUtil.ItemCallback<Search>() {
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.Title == newItem.Title
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem == newItem
            }


        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    override fun setState(newState: State) {
        if (super.getItemCount() != 0) {
            state = newState
            notifyDataSetChanged()

        }
    }
}
