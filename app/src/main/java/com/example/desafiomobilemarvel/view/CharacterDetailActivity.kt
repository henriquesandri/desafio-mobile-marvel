package com.example.desafiomobilemarvel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.view.adapter.ComicAdapter
import com.example.desafiomobilemarvel.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.activity_character_detail.*

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var mViewModel: CharacterDetailViewModel
    private lateinit var mDescription: String
    private lateinit var mName: String
    private lateinit var mThumbnail: String

    private var mId: Int = 0

    private val mAdapter = ComicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mViewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)

        val recycler = findViewById<RecyclerView>(R.id.recycler_comic)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = mAdapter

        loadDataFromActivity()
        setComponents()

        observe()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.list(mId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadDataFromActivity() {
        val bundle = intent.extras
        if (bundle != null) {
            mDescription = bundle.getString(MarvelConstants.BUNDLE.DESCRIPTION).toString()
            mId = bundle.getInt(MarvelConstants.BUNDLE.ID)
            mName = bundle.getString(MarvelConstants.BUNDLE.NAME).toString()
            mThumbnail = bundle.getString(MarvelConstants.BUNDLE.THUMBNAIL).toString()
        }
    }

    private fun setComponents() {
        text_name_detail.text = mName
        text_description_detail.text = mDescription
        Glide.with(this)
            .load(mThumbnail)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fallback(R.mipmap.ic_launcher)
            .into(image_character_detail)
    }

    private fun observe() {
        mViewModel.comics.observe(this, {
            if (it.data.results.count() > 0) {
                mAdapter.updateList(it.data.results)
                progress_bar_detail.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, R.string.msg_no_items, Toast.LENGTH_SHORT).show()
            }
        })
    }
}