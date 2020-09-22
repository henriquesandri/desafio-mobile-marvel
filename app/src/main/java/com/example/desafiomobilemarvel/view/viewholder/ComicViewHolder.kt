package com.example.desafiomobilemarvel.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.model.comic.ComicModel

class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mTextTitle: TextView = itemView.findViewById(R.id.text_title)
    private var mTextDescription: TextView = itemView.findViewById(R.id.text_description_comic)
    private var mTextNumPages: TextView = itemView.findViewById(R.id.text_num_pages)
    private var mImageComic: ImageView = itemView.findViewById(R.id.image_comic)

    fun bindData(comic: ComicModel) {

        this.mTextTitle.text = comic.title
        if (comic.description == "") {
            this.mTextDescription.text = itemView.context.getString(R.string.msg_no_description)
        } else {
            this.mTextDescription.text = comic.description
        }
        this.mTextNumPages.text = itemView.context.getString(R.string.num_pages, comic.pageCount)
        Glide.with(itemView)
            .load(comic.thumbnail.path + "." + comic.thumbnail.extension)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fallback(R.mipmap.ic_launcher)
            .into(this.mImageComic)
    }
}