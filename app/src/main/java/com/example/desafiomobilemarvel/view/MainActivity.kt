package com.example.desafiomobilemarvel.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.service.listener.CharacterListener
import com.example.desafiomobilemarvel.service.model.character.CharacterModel
import com.example.desafiomobilemarvel.view.adapter.CharacterAdapter
import com.example.desafiomobilemarvel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mListener: CharacterListener
    private val mAdapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val recycler = findViewById<RecyclerView>(R.id.recycler_character)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = mAdapter

        mListener = object : CharacterListener(
            recycler.layoutManager as LinearLayoutManager,
            {
                mViewModel.list()
            },
            MarvelConstants.PARAMS.OFFSET_VAL
        ) {
            override fun onListClick(character: CharacterModel) {
                val intent = Intent(applicationContext, CharacterDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putString(MarvelConstants.BUNDLE.DESCRIPTION, character.description)
                bundle.putInt(MarvelConstants.BUNDLE.ID, character.id)
                bundle.putString(MarvelConstants.BUNDLE.NAME, character.name)
                bundle.putString(
                    MarvelConstants.BUNDLE.THUMBNAIL,
                    character.thumbnail.path + "." + character.thumbnail.extension
                )
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
        mListener.let {
            recycler.addOnScrollListener(it)
        }

        observe()
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.list()
    }

    private fun observe() {
        mViewModel.characters.observe(this, {
            if (it.data.results.count() > 0) {
                mAdapter.updateList(it.data.results.toMutableList())
                progress_bar.visibility = View.INVISIBLE
                text_progress.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, R.string.msg_no_items, Toast.LENGTH_SHORT).show()
            }
        })
    }
}