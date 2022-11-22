package com.buvgm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    private lateinit var progres: ProgressBar
    private lateinit var imageState: ImageView
    private val LoginViewModel:LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin = view.findViewById(R.id.login_button)
        buttonNewAccount = view.findViewById(R.id.clicklabe_textView_register)
        inputEmail = view.findViewById(R.id.inputLayout_loginFragment_email)
        inputPassword = view.findViewById(R.id.inputLayout_loginFragment_password)
        imageState = view.findViewById(R.id.image_state)
        progres = view.findViewById(R.id.Progressbar_Login)

        authRepository = AuthRepositoryImpl(
            api = FirebaseAuthApiImpl()
        )

        setListeners()
        setObservers()
    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            LoginViewModel.statusApp.collectLatest {
                verStatus(it)
                delay(1000L)
            }
        }
    }

    private fun verStatus(it: LoginViewModel.StatusApp) {
        when (it){
            is LoginViewModel.StatusApp.default -> {
                buttonLogin.visibility = View.VISIBLE
                progres.visibility = View.GONE
            }
            is LoginViewModel.StatusApp.failure -> {
                buttonLogin.visibility = View.VISIBLE
                progres.visibility = View.GONE
                imageState.load(R.drawable.ic_error)
            }
            is LoginViewModel.StatusApp.loading -> {
                buttonLogin.visibility = View.GONE
                progres.visibility = View.VISIBLE
            }
            is LoginViewModel.StatusApp.succes -> {
                buttonLogin.visibility = View.GONE
                progres.visibility = View.VISIBLE
                imageState.load(R.drawable.twitter_like)
            }
        }

    }
    private fun setListeners() {
        buttonLogin.setOnClickListener {
            val email = inputEmail.editText!!.text.toString()
            val password = inputPassword.editText!!.text.toString()
            LoginViewModel.reset()

            lifecycleScope.launch {
                val id = authRepository.signInWithEmailAndPassword(email, password)
                if (!id.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Bienvenido",
                        Toast.LENGTH_LONG
                    ).show()
                    LoginViewModel.carga()
                    Intent(requireContext(), InternalActivity::class.java).apply {
                        putExtras(bundleOf("id" to id))
                        LoginViewModel.exito()
                        startActivity(this)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Usuario o contraseña incorrectos",
                        Toast.LENGTH_LONG
                    ).show()
                    LoginViewModel.fallo()
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