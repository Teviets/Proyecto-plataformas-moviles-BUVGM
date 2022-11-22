package com.buvgm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.load
import com.buvgm.R
import com.buvgm.data.model.remote.firebase.FirebaseAuthApiImpl
import com.buvgm.data.model.repository.auth.AuthRepository
import com.buvgm.data.model.repository.auth.AuthRepositoryImpl
import com.buvgm.databinding.FragmentLoginBinding
import com.buvgm.ui.InternalActivity
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var buttonLogin: Button
    private lateinit var buttonNewAccount: TextView
    private lateinit var authRepository: AuthRepository
    private lateinit var inputEmail: TextInputLayout
    private lateinit var inputPassword: TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin = view.findViewById(R.id.login_button)
        buttonNewAccount = view.findViewById(R.id.clicklabe_textView_register)
        inputEmail = view.findViewById(R.id.inputLayout_loginFragment_email)
        inputPassword = view.findViewById(R.id.inputLayout_loginFragment_password)

        authRepository = AuthRepositoryImpl(
            api = FirebaseAuthApiImpl()
        )

        setListeners()
    }

    private fun setListeners() {
        buttonLogin.setOnClickListener {
            val email = inputEmail.editText!!.text.toString()
            val password = inputPassword.editText!!.text.toString()

            lifecycleScope.launch {
                val id = authRepository.signInWithEmailAndPassword(email, password)
                if (!id.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Bienvenido",
                        Toast.LENGTH_LONG
                    ).show()
                    Intent(requireContext(), InternalActivity::class.java).apply {
                        putExtras(bundleOf("id" to id))
                        startActivity(this)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Usuario o contraseña incorrectos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        buttonNewAccount.setOnClickListener {
          navigateToListScreen()
        }
    }

    private fun navigateToListScreen() {
        CoroutineScope(Dispatchers.Main).launch {
            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToNewAccountFragment()
            )
        }
    }

}