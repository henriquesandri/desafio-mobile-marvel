package com.example.desafiomobilemarvel.view.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.listener.CharacterListener
import com.example.desafiomobilemarvel.service.model.CharacterModel

class CharacterViewHolder(itemView: View, val listener: CharacterListener) : RecyclerView.ViewHolder(itemView) {

    private var mTextName: TextView = itemView.findViewById(R.id.text_name)
    private var mTextDescription: TextView = itemView.findViewById(R.id.text_description)
    private var mImageCharacter: ImageView = itemView.findViewById(R.id.image_character)

    fun bindData(character: CharacterModel) {

        this.mTextName.text = character.name
        this.mTextDescription.text = character.description
        // this.mImageCharacter = character.thumbnail

        mTextDescription.setOnClickListener { listener.onListClick(character.id) }

    }

}