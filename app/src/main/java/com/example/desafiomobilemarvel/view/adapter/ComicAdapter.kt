package com.example.desafiomobilemarvel.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.model.comic.ComicModel
import com.example.desafiomobilemarvel.view.viewholder.ComicViewHolder

class ComicAdapter : RecyclerView.Adapter<ComicViewHolder>() {

    private var mList: List<ComicModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_comic_list, parent, false)
        return ComicViewHolder(item)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun updateList(list: List<ComicModel>) {
        mList = list
        notifyDataSetChanged()
    }
}