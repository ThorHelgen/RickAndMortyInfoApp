package com.thorhelgen.rickandmortyinfoapp.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thorhelgen.rickandmortyinfoapp.databinding.ListItemBinding
import androidx.recyclerview.widget.ListAdapter as RecyclerListAdapter

class ListAdapter(
    private val clickListener: (Int) -> Unit
) : RecyclerListAdapter<ListItem, ListAdapter.ListItemViewHolder>(DiffCallback){

    class ListItemViewHolder(
        private val binding: ListItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(
            item: ListItem,
            clickListener: (Int) -> Unit
        ) {
            binding.listItem = item
            binding.listItemView.setOnClickListener { clickListener(item.id) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder =
        ListItemViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}