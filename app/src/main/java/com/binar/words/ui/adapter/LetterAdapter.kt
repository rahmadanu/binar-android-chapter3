package com.binar.words.ui.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.words.R
import com.binar.words.databinding.LetterListItemBinding
import com.binar.words.ui.fragment.WordFragment
import com.binar.words.ui.model.Word
class LetterAdapter(private val fragment: Fragment) : RecyclerView.Adapter<LetterAdapter.ViewHolder>(){

/*    private val diffCallBack = object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.letter == newItem.letter
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)*/

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val letterList = ArrayList<String>()

    fun submitData(list: List<String>) {
        letterList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LetterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val letter = letterList[position]

        with(holder) {
            binding.btnLetter.text = letter

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(binding.btnLetter.text.toString())
            }
        }

    }

    override fun getItemCount(): Int = letterList.size

    class ViewHolder (val binding: LetterListItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(letter: String)
    }
}

