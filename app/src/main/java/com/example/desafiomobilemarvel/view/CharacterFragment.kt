package com.example.desafiomobilemarvel.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.service.listener.CharacterListener
import com.example.desafiomobilemarvel.view.adapter.CharacterAdapter
import com.example.desafiomobilemarvel.view.viewmodel.CharacterViewModel

class CharacterFragment : Fragment() {

    private lateinit var mViewModel: CharacterViewModel
    private lateinit var mListener: CharacterListener
    private val mAdapter = CharacterAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_character, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_character)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = mAdapter

        mListener = object : CharacterListener {
            override fun onListClick(id: Int) {
                //val intent = Intent(context, CharacterDetailsActivity::class.java)
                //val bundle = Bundle()
                //bundle.putInt(MarvelConstants.BUNDLE.TASKID, id)
                //intent.putExtras(bundle)
                //startActivity(intent)
            }
        }

        observe()

        return root;
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.list()
    }

    private fun observe() {
        mViewModel.characters.observe(viewLifecycleOwner, Observer {
            if (it.count() > 0) {
                mAdapter.updateList(it)
            }
        })
    }

}