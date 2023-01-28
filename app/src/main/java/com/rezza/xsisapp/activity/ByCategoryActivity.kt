package com.rezza.xsisapp.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rezza.xsisapp.R
import com.rezza.xsisapp.adapter.ByCategoryAdapter
import com.rezza.xsisapp.dialog.DetailDialog
import com.rezza.xsisapp.model.Movie
import com.rezza.xsisapp.utility.AssetReader
import com.rezza.xsisapp.utility.KeyBoardAction
import com.rezza.xsisapp.utility.KeyBoardAction.Companion.hideKeyboard
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class ByCategoryActivity : AppCompatActivity() {

    private lateinit var txvw_title : TextView
    private lateinit var card_search : CardView
    private lateinit var imvw_back : ImageView
    private lateinit var imvw_clear : ImageView
    private lateinit var rvly_search : RelativeLayout
    private lateinit var edtx_search : EditText
    private lateinit var byCategoryAdapter: ByCategoryAdapter

    private var listMovie = ArrayList<Movie>()
    private var listMovieFilter = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_by_category)

        txvw_title  = findViewById(R.id.txvw_title)
        imvw_back   = findViewById(R.id.imvw_back)
        card_search = findViewById(R.id.card_search)
        rvly_search = findViewById(R.id.rvly_search)
        imvw_clear  = findViewById(R.id.imvw_clear)
        edtx_search = findViewById(R.id.edtx_search)
        card_search.visibility = View.GONE

        val rcvwData = findViewById<RecyclerView>(R.id.rcvw_data)
        rcvwData.layoutManager = LinearLayoutManager(this)

        listMovie = ArrayList()
        listMovieFilter = ArrayList()
        byCategoryAdapter = ByCategoryAdapter(listMovieFilter, this)
        rcvwData.adapter = byCategoryAdapter

        initListener()
        create()
    }

    private fun initListener(){
        findViewById<RelativeLayout>(R.id.rvly_back).setOnClickListener {
            finish()
        }

        imvw_back.setOnClickListener {
            edtx_search.hideKeyboard()
            edtx_search.text = null
            card_search.clearAnimation()
            card_search.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_out))
            card_search.visibility = View.GONE

        }

        rvly_search.setOnClickListener {
            card_search.clearAnimation()
            card_search.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in))
            card_search.visibility = View.VISIBLE
            KeyBoardAction.showKeyboard(this,edtx_search)
        }

        imvw_clear.setOnClickListener{
            edtx_search.text = null
        }

        edtx_search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                findData(p0.toString())
            }

        })
    }

   private fun create(){
        val  title = intent.getStringExtra("title")
       txvw_title.text = title

       if (title.equals("Action")){
           loadData("action.txt")
       }
       else {
           loadData("latest.txt")
       }
   }

    private fun loadData(file : String){
        listMovie.clear()

        val sData = AssetReader.getStringFromAsset(this,file)
        if (sData != null) {
            val ja = JSONArray(sData)

            for (i in 0 until ja.length()){
                val jo = ja.getJSONObject(i)
                val id = jo.getString("imdbid")
                val title = jo.getString("title")
                if (!jo.has("synopsis")){
                    continue
                }
                val synopsis = jo.getString("synopsis")
                val images = jo.getJSONArray("imageurl")
                if (images.length() == 0){
                    continue
                }
                val image = images.getString(0)

                listMovie.add(Movie(id,title,synopsis,image))
            }
        }
        findData("")

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun findData(searchText : String){
        listMovieFilter.clear()
        byCategoryAdapter.searchText(searchText)

        if (searchText.isEmpty()){
            listMovieFilter.addAll(listMovie)
        }
        else {
            for (movie in listMovie){
                if (movie.title.uppercase().contains(searchText.uppercase())){
                    listMovieFilter.add(movie)
                }
            }
        }

        byCategoryAdapter.notifyDataSetChanged()
        byCategoryAdapter.setOnSelectedListener(object : ByCategoryAdapter.OnSelectedListener{
            override fun onSelected(movie: Movie) {
                showDetail(movie)
            }
        })
    }

    private fun showDetail(movie: Movie){
        val dialog = DetailDialog(this)
        dialog.show(movie)
    }
}