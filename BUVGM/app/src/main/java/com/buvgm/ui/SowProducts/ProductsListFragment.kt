package com.buvgm.ui.SowProducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.buvgm.R
import com.buvgm.data.model.local.entity.Place
import com.buvgm.data.model.remote.firebase.FirebasePlaceApiImpl
import com.buvgm.data.model.repository.place.PlaceRepository
import com.buvgm.data.model.repository.place.PlaceRepositoryImpl
import com.buvgm.databinding.FragmentProductsListBinding
import com.buvgm.ui.Adapter.ProductAdapter
import com.buvgm.ui.login.LoginFragmentDirections
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProductsListFragment : Fragment(),
ProductAdapter.RecyclerViewProductEvents{
    private lateinit var binding: FragmentProductsListBinding
    private lateinit var adapter: ProductAdapter

    private lateinit var repository: PlaceRepository

    private lateinit var products: MutableList<Place>

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

        repository = PlaceRepositoryImpl(
            FirebasePlaceApiImpl(
                Firebase.firestore
            )
        )
    }

    private fun setListeners() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home_item -> {
                    binding.searchTextInputLayout.visibility = View.GONE
                    binding.buttonSearch.visibility = View.GONE
                    this.products
                    getProducts()
                    adapter.notifyDataSetChanged()
                }
                R.id.search_item -> {
                    binding.searchTextInputLayout.visibility = View.VISIBLE
                    binding.buttonSearch.visibility = View.VISIBLE

                }
                R.id.favorites_item -> {
                    binding.searchTextInputLayout.visibility = View.GONE
                    binding.buttonSearch.visibility = View.GONE
                    getFavorites()
                    adapter.notifyDataSetChanged()

                }

            }
            true
        }
        binding.buttonSearch.setOnClickListener {
            getSearch()
            adapter.notifyDataSetChanged()
        }

    }

    private fun getProducts() {
        lifecycleScope.launchWhenStarted {
            products = repository.getPlace()!! as MutableList<Place>
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
        this.products = mutableListOf()
        if (products != null) {
            this.products = products as MutableList<Place>
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
        val action =ProductsListFragmentDirections.actionProductsListFragment2ToProductProfileFragment2()
        requireView().findNavController().navigate(action)
    }

    private fun getFavorites(){
        lifecycleScope.launchWhenStarted {
            products = repository.getPlaceFavorite(true) !! as MutableList<Place>
            if (products != null) {
                if (products!!.isEmpty()){
                    // api
                }else{
                    showProducts(products,false)
                }
            }
        }
    }

    private fun getSearch(){
        lifecycleScope.launchWhenStarted {
            products = repository
                .getPlaceSearch(
                    binding.searchTextInputLayout.editText!!.text.toString()
                )!! as MutableList<Place>
            if (products != null) {
                if (products!!.isEmpty()){
                    // api
                }else{
                    showProducts(products,false)
                }
            }
        }
    }






}