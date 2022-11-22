package com.buvgm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.buvgm.R
import com.buvgm.databinding.FragmentLoginBinding
import com.buvgm.ui.InternalActivity
import com.buvgm.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val LoginViewModel:LoginViewModel by viewModels()

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
            verificacion(
                email = binding.inputLayoutLoginFragmentEmail.editText!!.text.toString(),
                password = binding.inputLayoutLoginFragmentPassword.editText!!.text.toString(),
                //Mutable list de emails,
                // Mutable list de password
            )
        }
        binding.clicklabeTextViewRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            requireView().findNavController().navigate(action)
        }
    }

    private fun verificacion(email: String,password: String, emails: MutableList<String>, passwords: MutableList<String>){
        binding.loginButton.visibility = View.GONE
        binding.ProgressbarLogin.visibility = View.VISIBLE
        if ((emails.contains(email) == true) && (passwords.contains(password) == true)){
            val intent = Intent(context, InternalActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }else{
            Toast.makeText(requireContext(),getString(R.string.err_login),Toast.LENGTH_LONG).show()
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000L)
            }
            binding.loginButton.visibility = View.VISIBLE
            binding.ProgressbarLogin.visibility = View.GONE
        }

    }
}