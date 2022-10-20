package com.compose.movies.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.compose.movies.databinding.ShowItemBinding
import com.compose.movies.domain.model.Show
import com.compose.movies.framework.loadUrl


class ShowGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Show, ShowGridAdapter.ShowPropertyViewHolder>(DiffCallback) {

    class ShowPropertyViewHolder(private var binding: ShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(show: Show) {
            binding.title.text = show.name
            binding.reviewAverage.text = show.voteAverage.toString()
            binding.image.loadUrl("https://image.tmdb.org/t/p/w400/${show.posterPath}")
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean {
            return oldItem.name == newItem.name
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ShowPropertyViewHolder {
        return ShowPropertyViewHolder(ShowItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ShowPropertyViewHolder, position: Int) {
        val show = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(show)
        }
        holder.bind(show)
    }


    class OnClickListener(val clickListener: (property: Show) -> Unit) {
        fun onClick(show: Show) = clickListener(show)
    }
}
