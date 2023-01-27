package com.rezza.xsisapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rezza.xsisapp.R
import com.rezza.xsisapp.model.Movie


class ActionAdapter(private val  listMovie : ArrayList<Movie>, private val context: Context) : RecyclerView.Adapter<ActionAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int {
       return listMovie.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_action, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.txvw_title.text = listMovie[position].title
        Glide.with(context).load(listMovie[position].image).into(holder.imvw_image)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txvw_title = view.findViewById<TextView>(R.id.txvw_title);
        val imvw_image = view.findViewById<ImageView>(R.id.imvw_image);
    }


}