package com.binar.words.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.words.R
import com.binar.words.`interface`.OnItemClickCallback
import com.binar.words.databinding.FragmentWordBinding
import com.binar.words.ui.adapter.WordAdapter

class WordFragment : Fragment() {

    private var _binding: FragmentWordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val letter = arguments?.getString("letter")!!

        (activity as AppCompatActivity).supportActionBar?.title = "Words that start with $letter"
        val wordList = resources.getStringArray(R.array.word).toList().filter { word -> word.startsWith(letter) }

        val adapter = WordAdapter()
        val layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvWord.adapter = adapter
        binding.rvWord.layoutManager = layoutManager

        adapter.submitData(wordList)
        adapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(data: String) {
                openWebPage(data)
            }
        })
    }

    fun openWebPage(word: String) {
        val url = "https://www.google.com/search?q=$word"
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}