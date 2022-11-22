package com.buvgm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.buvgm.R
import com.buvgm.data.model.remote.firebase.FirebaseAuthApiImpl
import com.buvgm.data.model.repository.auth.AuthRepository
import com.buvgm.data.model.repository.auth.AuthRepositoryImpl
import com.buvgm.databinding.ActivityMainBinding
import com.buvgm.databinding.FragmentLoginBinding
import com.buvgm.ui.InternalActivity
import com.buvgm.ui.MainActivity
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var buttonLogin: Button
    private lateinit var buttonNewAccount: Button
    private lateinit var inputEmail: TextInputLayout
    private lateinit var inputPassword: TextInputLayout

    @Inject
    private lateinit var authRepository: AuthRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogin = view.findViewById(R.id.login_button)
        buttonNewAccount = view.findViewById(R.id.clicklabe_textView_register)
        inputEmail = view.findViewById(R.id.inputLayout_loginFragment_email)
        inputPassword = view.findViewById(R.id.inputLayout_loginFragment_password)

        setListeners()
        //authRepository = AuthRepositoryImpl{
        //   = FirebaseAuthApiImpl()
        //}
    }


    private fun setListeners() {
        buttonLogin.setOnClickListener {
            val email = inputEmail.editText!!.text.toString()
            val password = inputPassword.editText!!.text.toString()


            lifecycleScope.launch {
                val userId = authRepository.signInWithEmailAndPassword(email, password)
                if (!userId.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Usuario loggeado exitosamente",
                        Toast.LENGTH_LONG
                    ).show()
                    Intent(requireContext(), InternalActivity::class.java).apply {  //Mover hacia donde se requiere siguiente fragment
                        putExtras(bundleOf("id" to userId))
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
    }
}