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
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.load
import com.buvgm.R
import com.buvgm.data.model.remote.firebase.FirebaseAuthApiImpl
import com.buvgm.data.model.repository.auth.AuthRepository
import com.buvgm.data.model.repository.auth.AuthRepositoryImpl
import com.buvgm.databinding.FragmentNewAccountBinding
import com.buvgm.ui.InternalActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.util.jar.Manifest

class NewAccountFragment : Fragment(R.layout.fragment_new_account) {
    private val NewAccountViewModel: NewAccountViewModel by viewModels()

    private lateinit var buttonNew: Button
    private lateinit var inputE: TextInputLayout
    private lateinit var inputP: TextInputLayout
    private lateinit var inputCP: TextInputLayout
    private lateinit var authRepository: AuthRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonNew = view.findViewById(R.id.Register_button)
        inputE = view.findViewById(R.id.Correo_EditText)
        inputP = view.findViewById(R.id.Contraseña_EditText)
        inputCP = view.findViewById(R.id.Contraseña_DeNuevo_EditText)


        authRepository = AuthRepositoryImpl(
            api = FirebaseAuthApiImpl()
        )

        setListeners()

    }

    private fun setListeners() {
        buttonNew.setOnClickListener {
            val newEmail = inputE.editText!!.text.toString()
            val newPassword = inputP.editText!!.text.toString()
            val confirmPassword = inputCP.editText!!.text.toString()

            if(newEmail.isNotEmpty() && newPassword.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (newPassword == confirmPassword) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(newEmail, confirmPassword)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    requireContext(),
                                    "Usuario registrado",
                                    Toast.LENGTH_SHORT
                                ).show()
                                requireView().findNavController()
                                    .navigate(NewAccountFragmentDirections.actionNewAccountFragmentToLoginFragment())
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Error al registrar usuario",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Las contraseñas no coinciden",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }else{
                Toast.makeText(requireContext(), "Verifica tus datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}