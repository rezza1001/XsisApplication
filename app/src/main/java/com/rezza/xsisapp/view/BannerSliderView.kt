package com.rezza.xsisapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import com.github.islamkhsh.CardSliderIndicator
import com.github.islamkhsh.CardSliderViewPager
import com.rezza.xsisapp.R
import com.rezza.xsisapp.adapter.BannerAdapter
import com.rezza.xsisapp.dialog.DetailDialog
import com.rezza.xsisapp.model.Movie
import com.rezza.xsisapp.utility.AssetReader
import org.json.JSONArray

class BannerSliderView(context: Context?, attrs: AttributeSet?) : MyView(context, attrs) {

    private lateinit var pager_top : CardSliderViewPager
    private lateinit var pager_indicator : CardSliderIndicator
    private lateinit var bannerAdapter: BannerAdapter

    var listBanner = ArrayList<Movie>()


    override fun setlayout(): Int {
       return R.layout.view_topslider
    }


    override fun initLayout() {
        pager_top = findViewById(R.id.pager_top)
        pager_indicator = findViewById(R.id.pager_indicator)
    }

    override fun initListener() {

    }

    @SuppressLint("NotifyDataSetChanged")
    fun create(){
        listBanner = ArrayList()
        bannerAdapter = BannerAdapter(listBanner, context)

        pager_top.adapter = bannerAdapter

        val sData = AssetReader.getStringFromAsset(context,"banner.txt")
        if (sData != null) {
            val ja = JSONArray(sData)

            for (i in 0 until ja.length()){
                val jo = ja.getJSONObject(i)
                val id = jo.getString("imdbid")
                val title = jo.getString("title")
                val synopsis = jo.getString("synopsis")
                val images = jo.getJSONArray("imageurl")
                val image = images.getString(0)

                listBanner.add(Movie(id,title,synopsis,image))
            }
        }
        bannerAdapter.notifyDataSetChanged()

        bannerAdapter.setOnSelectedListener(object :BannerAdapter.OnSelectedListener{
            override fun onSelected(movie: Movie) {
                showDetail(movie)
            }

        })
    }

    private fun showDetail(movie: Movie){
        val dialog = DetailDialog(context as Activity?)
        dialog.show(movie)
    }


}