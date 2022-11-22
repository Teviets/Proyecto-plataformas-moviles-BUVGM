package com.buvgm.ui.nuevoproducto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.buvgm.R
import com.google.android.material.textfield.TextInputLayout

class NewProductFragment : Fragment(R.layout.fragment_new_product) {

    private lateinit var buttonUpload: Button
    private lateinit var inputNombre: TextInputLayout
    private lateinit var inputDescripcion: TextInputLayout
    private lateinit var inputPrecio: TextInputLayout
    private lateinit var inputContacto: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_product, container, false)
        
        buttonUpload = view.findViewById(R.id.button_Upload)
    }

}