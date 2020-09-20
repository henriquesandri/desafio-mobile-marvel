package com.example.desafiomobilemarvel.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.listener.CharacterListener
import com.example.desafiomobilemarvel.view.adapter.CharacterAdapter
import com.example.desafiomobilemarvel.viewmodel.MainViewModel

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

        mListener = object : CharacterListener {
            override fun onListClick(id: Int) {
                val intent = Intent(applicationContext, CharacterDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("id", id)
                intent.putExtras(bundle)
                startActivity(intent)
            }
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
                mAdapter.updateList(it.data.results)
            } else {
                Toast.makeText(this, R.string.msg_no_items, Toast.LENGTH_SHORT).show()
            }
        })
    }
}