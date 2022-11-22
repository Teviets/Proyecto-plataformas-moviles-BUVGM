package com.buvgm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.buvgm.data.model.repository.auth.AuthRepository
import com.buvgm.databinding.FragmentLoginBinding
import com.buvgm.ui.InternalActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val LoginViewModel:LoginViewModel by viewModels()
    @Inject
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
            binding.loginButton.visibility = View.GONE
            binding.ProgressbarLogin.visibility = View.VISIBLE
            val intent = Intent(context, InternalActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000L)
            }

        verificacion(
            email = binding.inputLayoutLoginFragmentEmail.editText!!.text.toString(),
            password = binding.inputLayoutLoginFragmentPassword.editText!!.text.toString()
        )

        }
        binding.clicklabeTextViewRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            requireView().findNavController().navigate(action)
        }
    }

    private fun verificacion(email: String,password: String){
        binding.loginButton.visibility = View.GONE
        binding.ProgressbarLogin.visibility = View.VISIBLE
        binding.loginButton.setOnClickListener {
            val email = binding.inputLayoutLoginFragmentEmail.editText!!.text.toString()
            val password = binding.inputLayoutLoginFragmentPassword.editText!!.text.toString()


            lifecycleScope.launch {
                val userId = authRepository.signInWithEmailAndPassword(email, password)
                if (!userId.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Usuario loggeado exitosamente",
                        Toast.LENGTH_LONG
                    ).show()
                    Intent(requireContext(), InternalActivity::class.java).apply {  //Mover hacia donde se requiere siguiente fragment
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        putExtras(bundleOf("id" to userId))
                        startActivity(this)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Usuario o contraseña incorrectos",
                        Toast.LENGTH_LONG
                    ).show()
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000L)
                    }
                    binding.loginButton.visibility = View.VISIBLE
                    binding.ProgressbarLogin.visibility = View.GONE
                }
            }
        }

    }
}