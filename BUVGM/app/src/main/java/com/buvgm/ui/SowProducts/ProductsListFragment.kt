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
import com.buvgm.data.model.Product
import com.buvgm.databinding.FragmentProductsListBinding
import com.buvgm.ui.Adapter.ProductAdapter
import com.buvgm.ui.configuracion.SettingsFragment
import com.buvgm.ui.configuracion.SettingsFragmentDirections
import com.buvgm.ui.nuevoproducto.NewProductFragment

class ProductsListFragment : Fragment(),
ProductAdapter.RecyclerViewProductEvents{
    private lateinit var binding: FragmentProductsListBinding
    private lateinit var adapter: ProductAdapter
    //private lateinit var database: ProyectDataBase


    private var products: MutableList<Product> = mutableListOf()

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
        binding.principalToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.nuevoProducto_item -> {
                    binding.principalToolbar.visibility = View.GONE
                    val action = ProductsListFragmentDirections.actionProductsListFragmentToNewProductFragment()
                    requireView().findNavController().navigate(action)
                    true
                }
                R.id.settings_item -> {
                    binding.principalToolbar.visibility = View.GONE
                    val action = ProductsListFragmentDirections.actionProductsListFragmentToSettingsFragment()
                    requireView().findNavController().navigate(action)

                    true
                }
                else -> true
            }
        }
    }

    private fun getProducts() {
        lifecycleScope.launchWhenStarted {
            //val products = database.productDao().getProducts()
            if (products.isEmpty()){
                // api
            }else{
                showProducts(products,false)
            }
        }
    }

    private fun showProducts(products: List<Product>, isSync: Boolean) {
        // visibilidad
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
        binding.apply {
            theRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            theRecyclerView.setHasFixedSize(true)
            theRecyclerView.adapter = adapter
        }
    }

    override fun onItemClicked(product: Product){
        val action = ProductsListFragmentDirections.actionProductsListFragmentToProductProfileFragment(
            product.id
        )
        requireView().findNavController().navigate(action)
    }



}