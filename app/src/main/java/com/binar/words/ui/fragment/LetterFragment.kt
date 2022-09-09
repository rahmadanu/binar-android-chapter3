package com.binar.words.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.words.R
import com.binar.words.`interface`.OnDataPass
import com.binar.words.`interface`.OnItemClickCallback
import com.binar.words.databinding.FragmentLetterBinding
import com.binar.words.model.Word
import com.binar.words.ui.adapter.LetterAdapter

class LetterFragment : Fragment() {

    private var _binding: FragmentLetterBinding? = null
    private val binding get() = _binding!!

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

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val letterRawList = resources.getStringArray(R.array.letter).toList()

        val letterList = ArrayList<Word>()

        for (element in letterRawList) {
            letterList.add(Word(letter = element))
        }

        val adapter = LetterAdapter()
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvLetter.adapter = adapter
        binding.rvLetter.layoutManager = layoutManager

        adapter.submitData(letterList)

        adapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemClicked(data: String) {
                Toast.makeText(requireContext(), "this is $data", Toast.LENGTH_SHORT).show()

                val action = LetterFragmentDirections.actionLetterFragmentToWordFragment(data)
                findNavController().navigate(action)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}