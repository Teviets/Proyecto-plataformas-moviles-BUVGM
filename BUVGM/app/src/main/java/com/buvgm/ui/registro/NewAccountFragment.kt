package com.buvgm.ui.registro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.buvgm.R
import com.buvgm.databinding.FragmentNewAccountBinding

class NewAccountFragment : Fragment() {
    private val NewAccountViewModel: NewAccountViewModel by viewModels()

    private lateinit var binding: FragmentNewAccountBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

}