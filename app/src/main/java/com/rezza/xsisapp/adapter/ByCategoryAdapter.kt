package com.rezza.xsisapp.adapter

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rezza.xsisapp.R
import com.rezza.xsisapp.model.Movie
import com.rezza.xsisapp.utility.CustomTypefaceSpan
import java.util.*


class ByCategoryAdapter(private val  listMovie : ArrayList<Movie>, private val context: Context)
    : RecyclerView.Adapter<ByCategoryAdapter.MovieViewHolder>() {

    private lateinit var searchText : String

    override fun getItemCount(): Int {
       return listMovie.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_by_category, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val title = listMovie[position].title
        val start = title.uppercase(Locale.getDefault()).indexOf(searchText.uppercase(Locale.getDefault()))
        val end = start + searchText.length

        holder.txvw_title.text = boldText(title, start, end)
        holder.txvw_description.text = listMovie[position].description
        Glide.with(context).load(listMovie[position].image).into(holder.imvw_image)
        holder.card_body.setOnClickListener{
            onSelectedListener.onSelected(listMovie[position])
        }
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txvw_title = view.findViewById<TextView>(R.id.txvw_title);
        val txvw_description = view.findViewById<TextView>(R.id.txvw_description);
        val imvw_image = view.findViewById<ImageView>(R.id.imvw_image);
        val card_body = view.findViewById<CardView>(R.id.card_body);


    }


    fun boldText(text : String, start : Int, end : Int) : SpannableString{
        val content = SpannableString(text)
        val font = ResourcesCompat.getFont(context, R.font.roboto_bold)
        content.setSpan(CustomTypefaceSpan("",font!!),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        content.setSpan(ForegroundColorSpan(Color.parseColor("#ff8f00")),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return content
    }

    fun searchText(text : String){
        searchText = text
    }


    private lateinit var onSelectedListener: OnSelectedListener

    fun setOnSelectedListener(onSelectedListener: OnSelectedListener){
        this.onSelectedListener = onSelectedListener
    }
    interface  OnSelectedListener{
        fun onSelected(movie: Movie)
    }
}