package com.thorhelgen.rickandmortyinfoapp.presentation.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thorhelgen.rickandmortyinfoapp.databinding.GridItemBinding

class GridAdapter (private val clickListener: (Int) -> Unit) :
    ListAdapter<GridItem, GridAdapter.GridItemViewHolder>(DiffCallback) {

    class GridItemViewHolder (
        private val binding: GridItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: GridItem,
            clickListener: (Int) -> Unit
        ) {
            binding.gridItem = item
            binding.gridItemView.setOnClickListener { clickListener(item.id) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GridItem>() {
        override fun areItemsTheSame(oldItem: GridItem, newItem: GridItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GridItem, newItem: GridItem): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder =
        GridItemViewHolder(
            GridItemBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}