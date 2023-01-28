package com.rezza.xsisapp.dialog

import android.app.Activity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.rezza.xsisapp.R
import com.rezza.xsisapp.model.Movie
import com.rezza.xsisapp.view.LatestView

class DetailDialog(pContext: Activity?) : MyDialog(pContext) {

    private lateinit var card_body : CardView
    private lateinit var imvw_image : ImageView
    private lateinit var txvw_title : TextView
    private lateinit var txvw_description : TextView
    private lateinit var latest_view : LatestView

    private var activity = pContext

    override fun setLayout(): Int {
        return R.layout.dialog_detail
    }

    override fun initLayout(view: View) {

        view.findViewById<RelativeLayout>(R.id.rvly_close).setOnClickListener{
            dismiss()
        }

        view.findViewById<RelativeLayout>(R.id.rvly_play).setOnClickListener{
            play()
        }

        imvw_image = view.findViewById(R.id.imvw_image)
        card_body = view.findViewById(R.id.card_body)
        txvw_title = view.findViewById(R.id.txvw_title)
        txvw_description = view.findViewById(R.id.txvw_description)
        latest_view = view.findViewById(R.id.latest_view)

        card_body.visibility = View.INVISIBLE

    }


    fun show(movie: Movie) {
        super.show()
        card_body.visibility = View.VISIBLE
        card_body.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))
        Glide.with(context).load(movie.image).placeholder(R.drawable.no_image).into(imvw_image)
        txvw_title.text = movie.title
        txvw_description.text = movie.description

        latest_view.create()
        latest_view.setOnShowDetailListener(object :LatestView.OnShowDetailListener{
            override fun onShowDetail(movie: Movie) {
                dismiss()
            }
        })

    }

    override fun onBackPressed() {
        dismiss()
    }

    private fun play(){
        val videoPlayerDialog = VideoPlayerDialog(activity)
        videoPlayerDialog.show("","")
    }
}