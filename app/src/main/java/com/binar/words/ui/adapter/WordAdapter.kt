package com.binar.words.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.words.`interface`.OnItemClickCallback
import com.binar.words.databinding.LetterListItemBinding
import com.binar.words.model.Word

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.letter == newItem.letter
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(list: ArrayList<Word>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LetterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(differ.currentList[position]) {

                binding.btnLetter.text = word

                binding.btnLetter.setOnClickListener {
                    onItemClickCallback.onItemClicked(word)
                }
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    class ViewHolder(val binding: LetterListItemBinding) : RecyclerView.ViewHolder(binding.root)
}