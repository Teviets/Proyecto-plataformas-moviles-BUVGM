package com.buvgm.ui.nuevoproducto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.buvgm.R
import com.buvgm.databinding.FragmentNewProductBinding

class NewProductFragment : Fragment() {
    private lateinit var binding: FragmentNewProductBinding
    private val NewProductViewModel: NewProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewProductBinding.inflate(inflater,container,false)
        return binding.root
    }

}