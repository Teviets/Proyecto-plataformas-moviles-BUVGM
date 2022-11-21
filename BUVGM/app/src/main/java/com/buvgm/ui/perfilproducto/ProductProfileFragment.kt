package com.buvgm.ui.perfilproducto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.buvgm.R
import com.buvgm.databinding.FragmentProductProfileBinding


class ProductProfileFragment : Fragment(R.layout.fragment_product_profile) {
    private lateinit var binding: FragmentProductProfileBinding
    private val ProductProfileViewModel: ProductProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

}