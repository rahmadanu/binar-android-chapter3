package com.binar.words.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.words.R
import com.binar.words.`interface`.OnDataPass
import com.binar.words.databinding.FragmentLetterBinding
import com.binar.words.ui.adapter.LetterAdapter

class LetterFragment : Fragment() {

    private var _binding: FragmentLetterBinding? = null
    private val binding get() = _binding!!

    private lateinit var onDataPass: OnDataPass

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*
        val letter = resources.getStringArray(R.array.letter)
        val word = resources.getStringArray(R.array.word)
*/
        val list = resources.getStringArray(R.array.letter).toList()

        val adapter = LetterAdapter(this)
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvLetter.adapter = adapter
        binding.rvLetter.layoutManager = layoutManager

        adapter.submitData(list)

        onDataPass = activity as OnDataPass

        adapter.setOnItemClickCallback(object : LetterAdapter.OnItemClickCallback {
            override fun onItemClicked(letter: String) {
                Toast.makeText(requireContext(), "this is $letter", Toast.LENGTH_SHORT).show()
                onDataPass.onDataPass(letter)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}