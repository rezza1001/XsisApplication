package com.rezza.xsisapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import com.rezza.xsisapp.R
import com.rezza.xsisapp.view.ActionView
import com.rezza.xsisapp.view.BannerSliderView
import com.rezza.xsisapp.view.LatestView

class MainActivity : AppCompatActivity() {

    lateinit var view_slider : BannerSliderView
    lateinit var action_view : ActionView
    lateinit var rvly_search : RelativeLayout
    lateinit var popular_view : LatestView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_slider = findViewById(R.id.view_slider)
        popular_view = findViewById(R.id.popular_view)
        action_view = findViewById(R.id.action_view)
        rvly_search = findViewById(R.id.rvly_search)

        create()
    }

   private fun create(){
       view_slider.create()
       popular_view.create()
       action_view.create()

       rvly_search.setOnClickListener{
           startActivity(Intent(this, SearchActivity::class.java))
       }
   }
}