package com.buvgm.ui.perfilproducto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.buvgm.R
import com.buvgm.data.model.Product
import com.buvgm.databinding.FragmentProductProfileBinding


class ProductProfileFragment : Fragment() {
    //private val args: ProductProfileFragmentArgs by navArgs()
    private var producto: Product? = null
    private var isLike: Boolean = false
    private lateinit var binding: FragmentProductProfileBinding
    //private lateinit var database: ProyectDataBase
    private val ProductProfileViewModel: ProductProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProduct()
        //setListener()
    }

    private fun getProduct() {
        lifecycleScope.launchWhenStarted {
            //val productlocal = database.productDao().getProduct(args.id)
            /*if (productlocal == null){
                Toast.makeText(requireContext(), getString(R.string.error_character_not_found), Toast.LENGTH_LONG).show()
                requireActivity().onBackPressed()
            }else{
                producto = productlocal
                setData()
            }*/
        }
    }

    private fun setData() {
        producto?.apply {
            binding.nombreLbl.text = name
            binding.precioLbl.text = precio
            binding.contactoLbl.text = contacto
            binding.descripcionLbl.text = description
            isLike = fav
        }
    }

    private fun setListener() {
        binding.animation.setOnClickListener{
            isLike = likeAnimation(binding.animation,R.raw.animacion_fav,isLike)
        }
    }

    private fun likeAnimation(imageView: LottieAnimationView, animation: Int, like: Boolean): Boolean {
        if (!like){
            imageView.setAnimation(animation)
            imageView.playAnimation()
        }else{
            imageView.setImageResource(R.drawable.twitter_like)
        }

        return !like

    }


}