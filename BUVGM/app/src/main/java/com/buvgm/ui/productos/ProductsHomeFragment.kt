package com.buvgm.ui.productos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.buvgm.R
import com.buvgm.databinding.FragmentNewAccountBinding
import com.buvgm.databinding.FragmentProductsHomeBinding


class ProductsHomeFragment : Fragment(R.layout.fragment_products_home) {
    private val ProductsHomeViewModel: ProductsHomeViewModel by viewModels()
    private lateinit var binding: FragmentProductsHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

}