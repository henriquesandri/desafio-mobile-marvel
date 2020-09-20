package com.example.desafiomobilemarvel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.view.adapter.ComicAdapter
import com.example.desafiomobilemarvel.viewmodel.CharacterDetailViewModel

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var mViewModel: CharacterDetailViewModel
    private val mAdapter = ComicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        mViewModel = ViewModelProvider(this).get(CharacterDetailViewModel::class.java)

        val recycler = findViewById<RecyclerView>(R.id.recycler_comic)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = mAdapter

        observe()
    }

    override fun onResume() {
        super.onResume()
        mViewModel.list(1011334)
    }

    private fun observe() {
        mViewModel.comics.observe(this, {
            if (it.data.results.count() > 0) {
                mAdapter.updateList(it.data.results)
            } else {
                Toast.makeText(this, R.string.msg_no_items, Toast.LENGTH_SHORT).show()
            }
        })
    }
}