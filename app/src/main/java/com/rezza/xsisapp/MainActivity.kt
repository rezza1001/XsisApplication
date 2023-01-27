package com.rezza.xsisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rezza.xsisapp.view.ActionView
import com.rezza.xsisapp.view.BannerSliderView
import com.rezza.xsisapp.view.LatestView

class MainActivity : AppCompatActivity() {

    lateinit var view_slider : BannerSliderView
    lateinit var action_view : ActionView
    lateinit var popular_view : LatestView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_slider = findViewById(R.id.view_slider)
        popular_view = findViewById(R.id.popular_view)
        action_view = findViewById(R.id.action_view)

        create()
    }

   private fun create(){
       view_slider.create()
       popular_view.create()
       action_view.create()
   }
}