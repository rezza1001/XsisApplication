package com.rezza.xsisapp.dialog

import android.app.Activity
import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.MediaController
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.cardview.widget.CardView
import com.rezza.xsisapp.R

class VideoPlayerDialog(context: Activity?) : MyDialog(context) {

    private lateinit var card_body : CardView
    private lateinit var vidio_view : VideoView
    private lateinit var txvw_load : TextView

    override fun setLayout(): Int {
        return R.layout.dialog_videoplayer
    }

    override fun initLayout(view: View) {

        view.findViewById<RelativeLayout>(R.id.rvly_close).setOnClickListener{
            dismiss()
        }

        card_body = view.findViewById(R.id.card_body)
        vidio_view = view.findViewById(R.id.vidio_view)
        txvw_load = view.findViewById(R.id.txvw_load)

        card_body.visibility = View.INVISIBLE
//        vidio_view.visibility = View.GONE
    }


    fun show(title: String, url: String) {
        super.show()
        card_body.visibility = View.VISIBLE
        card_body.startAnimation(AnimationUtils.loadAnimation(context, R.anim.zoom_in))

        play()

    }

    override fun onBackPressed() {
        dismiss()
    }

    private fun play(){
        val urlVideo = "https://shorturl.at/hlHQ8"

        val uri = Uri.parse(urlVideo)
        vidio_view.setVideoURI(uri)
//        vidio_view.setVideoPath(urlVideo)

        val mediaController = MediaController(context)
        mediaController.setAnchorView(vidio_view)
        mediaController.setMediaPlayer(vidio_view)

        vidio_view.setMediaController(mediaController)
        vidio_view.setOnPreparedListener {
            txvw_load.visibility = View.GONE
//            vidio_view.visibility = View.VISIBLE
        }
        vidio_view.start()
    }
}