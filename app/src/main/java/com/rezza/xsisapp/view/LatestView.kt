package com.rezza.xsisapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezza.xsisapp.R
import com.rezza.xsisapp.adapter.LatestAdapter
import com.rezza.xsisapp.model.Movie
import com.rezza.xsisapp.utility.AssetReader
import org.json.JSONArray

class LatestView(context: Context?, attrs: AttributeSet?) : MyView(context, attrs) {

    private lateinit var rcvw_popular : RecyclerView
    private lateinit var latestAdapter: LatestAdapter

    var listBanner = ArrayList<Movie>()

    override fun setlayout(): Int {
        return R.layout.view_latest
    }

    override fun initLayout() {
        rcvw_popular = findViewById(R.id.rcvw_latest);
        rcvw_popular.layoutManager = GridLayoutManager(context,2)
    }

    override fun initListener() {
    }

    @SuppressLint("NotifyDataSetChanged")
    fun create(){
        latestAdapter = LatestAdapter(listBanner, context)
        rcvw_popular.adapter = latestAdapter

        val sData = AssetReader.getStringFromAsset(context,"latest.txt")
        if (sData != null) {
            val ja = JSONArray(sData)

            for (i in 0 until 2){
                val jo = ja.getJSONObject(i)
                val id = jo.getString("imdbid")
                val title = jo.getString("title")
                val synopsis = jo.getString("synopsis")
                val images = jo.getJSONArray("imageurl")
                val image = images.getString(0)

                listBanner.add(Movie(id,title,synopsis,image))
            }
        }
        latestAdapter.notifyDataSetChanged()
    }
}