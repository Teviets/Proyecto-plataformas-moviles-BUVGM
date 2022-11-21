package com.buvgm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.buvgm.R
import com.buvgm.data.model.repository.auth.AuthRepository
import com.buvgm.databinding.FragmentLoginBinding
import com.buvgm.ui.InternalActivity
import com.buvgm.ui.MainActivity

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var authRepository: AuthRepository

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
            val intent = Intent(context, InternalActivity::class.java)
            startActivity(intent)
        }
        binding.clicklabeTextViewRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            requireView().findNavController().navigate(action)
            //Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_newAccountFragment)
        }
    }
}