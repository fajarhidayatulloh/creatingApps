package com.example.dev.creatingapps.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.example.dev.creatingapps.R
import com.example.dev.creatingapps.model.DataHome
//import com.example.dev.creatingapps.view.ui.DetailActivity
import kotlinx.android.synthetic.main.adapter_item_main.view.*


class MainAdapter(private val context: Context, private val arrayData: ArrayList<DataHome>)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val dataList: DataHome? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MainAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_item_main, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MainAdapter.ViewHolder, i: Int) {

    }
    override fun getItemCount(): Int {
        return arrayData.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init{
            itemView.textViewAdapter
            itemView.imageViewAdapter
            itemView.linearAdapter
        }
    }

}

