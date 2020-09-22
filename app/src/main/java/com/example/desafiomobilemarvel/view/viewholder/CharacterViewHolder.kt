package com.example.desafiomobilemarvel.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.listener.CharacterListener
import com.example.desafiomobilemarvel.service.model.character.CharacterModel

class CharacterViewHolder(itemView: View, private val listener: CharacterListener) :
    RecyclerView.ViewHolder(itemView) {

    private var mTextName: TextView = itemView.findViewById(R.id.text_name)
    private var mTextDescription: TextView = itemView.findViewById(R.id.text_description)
    private var mImageCharacter: ImageView = itemView.findViewById(R.id.image_character)

    fun bindData(character: CharacterModel) {

        this.mTextName.text = character.name
        if (character.description == "") {
            this.mTextDescription.text = itemView.context.getString(R.string.msg_no_description)
        } else {
            this.mTextDescription.text = character.description
        }
        Glide.with(itemView)
            .load(character.thumbnail.path + "." + character.thumbnail.extension)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fallback(R.mipmap.ic_launcher)
            .into(this.mImageCharacter)

        itemView.setOnClickListener { listener.onListClick(character) }
    }
}