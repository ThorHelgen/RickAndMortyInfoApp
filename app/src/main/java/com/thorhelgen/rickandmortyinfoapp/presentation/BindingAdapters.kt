package com.thorhelgen.rickandmortyinfoapp.presentation

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.grid.GridAdapter
import com.thorhelgen.rickandmortyinfoapp.presentation.mainscreen.grid.GridItem


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GridItem>) {
    val adapter = recyclerView.adapter as GridAdapter
    adapter.submitList(data)
}