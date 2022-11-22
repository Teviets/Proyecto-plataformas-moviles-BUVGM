package com.buvgm.ui.SowProducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.buvgm.R
import com.buvgm.data.model.local.entity.Place
import com.buvgm.data.model.repository.place.PlaceRepository
import com.buvgm.databinding.FragmentProductsListBinding
import com.buvgm.ui.Adapter.ProductAdapter


class ProductsListFragment : Fragment(),
ProductAdapter.RecyclerViewProductEvents{
    private lateinit var binding: FragmentProductsListBinding
    private lateinit var adapter: ProductAdapter
    private lateinit var repository: PlaceRepository

    private lateinit var products: List<Place>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        getProducts()
    }

    private fun setListeners() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home_item -> {
                    binding.searchTextInputLayout.visibility = View.GONE
                    /*this.products.shuffle()
                    adapter.notifyDataSetChanged()*/
                }
                R.id.search_item -> {
                    binding.searchTextInputLayout.visibility = View.VISIBLE
                    /*this.products.shuffle()
                    adapter.notifyDataSetChanged()

                    lifecycleScope.launchWhenStarted {
                        var txt = binding.
                    }*/
                }
                R.id.favorites_item -> {
                    binding.searchTextInputLayout.visibility = View.GONE
                    /*//val favProducts = database.getFavProducts()
                    //this.products = favProducts
                    adapter.notifyDataSetChanged()*/
                }

            }
            true
        }

    }

    private fun getProducts() {
        lifecycleScope.launchWhenStarted {
            //products = repository.getPlace()!!
            if (products != null) {
                if (products!!.isEmpty()){
                    // api
                }else{
                    showProducts(products,false)
                }
            }
        }
    }

    private fun showProducts(products: List<Place>?, isSync: Boolean) {
        // visibilidad
        this.products = listOf()
        if (products != null) {
            this.products = products
        }

        if (!isSync){
            setUpRecycler()
        }else{
            adapter.notifyDataSetChanged()
        }

    }

    private fun setUpRecycler() {
        adapter = ProductAdapter(this.products as MutableList<Place>,this)
        binding.apply {
            theRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            theRecyclerView.setHasFixedSize(true)
            theRecyclerView.adapter = adapter
        }
    }

    override fun onItemClicked(product: Place){
        /*val action = ProductsListFragmentDirections.actionProductsListFragmentToProductProfileFragment(
            product.id
        )
        requireView().findNavController().navigate(action)*/
    }





}