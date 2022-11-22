package com.buvgm.ui.productos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.buvgm.data.model.Product
import com.buvgm.databinding.FragmentProductsHomeBinding
import com.buvgm.ui.Adapter.ProductAdapter

class ProductsHomeFragment : Fragment() {

    private lateinit var binding: FragmentProductsHomeBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var database: ProyectDataBase

    private val products: MutableList<Product> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getProducts()
    }

    private fun getProducts() {
        lifecycleScope.launchWhenStarted {
            val products = database.ProductDao().getProducts()
            if (products.isEmpty()){
                // No verifica api
            }else{
                showProducts(products,false)
            }
        }
    }

    private fun showProducts(products: List<Product>, isSync: Boolean) {
        this.products.clear()
        this.products.addAll(products)

        if (!isSync){
            setUpRecycler()
        }else{
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecycler() {
        adapter = ProductAdapter(this.products,this)
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.homeRecyclerView.setHasFixedSize(true)
        binding.homeRecyclerView.adapter = adapter
    }


}