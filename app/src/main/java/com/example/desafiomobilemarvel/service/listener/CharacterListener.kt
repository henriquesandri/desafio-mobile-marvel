package com.example.desafiomobilemarvel.service.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilemarvel.service.model.character.CharacterModel

abstract class CharacterListener(
        private val layoutManager: LinearLayoutManager,
        private val askForMore: () -> Unit,
        private val offset: Int
) : RecyclerView.OnScrollListener() {
    private var finishedPagination: Boolean = false

    abstract fun onListClick(character: CharacterModel)

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()

        if (!finishedPagination && visibleItemCount + firstVisiblePosition >= totalItemCount - offset && firstVisiblePosition >= 0 && totalItemCount >= offset) {
            askForMore()
        }
    }

}