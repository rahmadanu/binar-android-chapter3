package com.binar.words.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binar.words.databinding.WordListItemBinding

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    private val wordList = ArrayList<String>()

    fun submitData(list: List<String>) {
        wordList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WordListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]

        with(holder) {
            binding.btnLetter.text = word
        }
    }

    override fun getItemCount(): Int = wordList.size

    class ViewHolder(val binding: WordListItemBinding) : RecyclerView.ViewHolder(binding.root)
}