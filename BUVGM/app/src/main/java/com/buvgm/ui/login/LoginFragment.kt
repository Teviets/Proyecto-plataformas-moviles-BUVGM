package com.buvgm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.buvgm.databinding.FragmentLoginBinding
import com.buvgm.ui.InternalActivity

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners(){
        binding.loginButton.setOnClickListener{
            val intent = Intent(activity, InternalActivity::class.java)
            startActivity(intent)
        }
        binding.clicklabeTextViewRegister.setOnClickListener {
            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            )
        }
    }
}