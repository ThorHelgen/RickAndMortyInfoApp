package com.thorhelgen.rickandmortyinfoapp.presentation

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thorhelgen.rickandmortyinfoapp.presentation.list.ListAdapter
import com.thorhelgen.rickandmortyinfoapp.presentation.list.ListItem
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridAdapter
import com.thorhelgen.rickandmortyinfoapp.presentation.grid.GridItem


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GridItem>) {
    val adapter = recyclerView.adapter as GridAdapter
    adapter.submitList(data)
}

@JvmName("bindRecyclerView1")
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ListItem>) {
    val adapter = recyclerView.adapter as ListAdapter
    adapter.submitList(data)
}

@BindingAdapter("srcBitmap")
fun bindBitmap(imageView: ImageView, bitmap: Bitmap?) {
    bitmap?.let {
        imageView.setImageBitmap(it)
    }
}
