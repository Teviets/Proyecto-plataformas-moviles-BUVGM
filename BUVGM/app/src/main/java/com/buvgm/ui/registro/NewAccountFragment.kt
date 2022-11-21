package com.buvgm.ui.registro

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.buvgm.R
import com.buvgm.databinding.FragmentNewAccountBinding
import com.buvgm.ui.InternalActivity
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.util.jar.Manifest

class NewAccountFragment : Fragment() {
    private val NewAccountViewModel: NewAccountViewModel by viewModels()

    private lateinit var binding: FragmentNewAccountBinding
    private val SELECT_ACTIVITY = 1
    private var imageUri : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.RegisterButton.setOnClickListener {
            verificacion()
        }
    }

    private fun verificacion() {
        TODO("Not yet implemented")
    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            NewAccountViewModel.statusApp.collectLatest {
                verStatus(it)
            }
        }
    }

    private fun verStatus(it: NewAccountViewModel.StatusApp) {
        when(it){
            is NewAccountViewModel.StatusApp.default ->{
                binding.RegisterButton.visibility = View.VISIBLE
                binding.progressBarNewAccount.visibility = View.GONE
            }
            is NewAccountViewModel.StatusApp.loading ->{
                binding.RegisterButton.visibility = View.GONE
                binding.progressBarNewAccount.visibility = View.VISIBLE
            }
            is NewAccountViewModel.StatusApp.failure ->{
                binding.RegisterButton.visibility = View.VISIBLE
                binding.progressBarNewAccount.visibility = View.GONE
                binding.errImg.load(R.drawable.ic_error)
                binding.errImg.visibility = View.VISIBLE
            }
            is NewAccountViewModel.StatusApp.succes ->{
                binding.errImg.load(R.drawable.usericon)
                binding.errImg.visibility = View.VISIBLE
            }
    }
}







}