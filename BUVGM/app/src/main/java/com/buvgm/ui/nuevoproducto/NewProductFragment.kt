package com.buvgm.ui.nuevoproducto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.buvgm.R
import com.buvgm.data.model.local.entity.Place
import com.buvgm.data.model.remote.firebase.FirebasePlaceApiImpl
import com.buvgm.data.model.repository.place.PlaceRepository
import com.buvgm.data.model.repository.place.PlaceRepositoryImpl
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class NewProductFragment : Fragment(R.layout.fragment_new_product) {

    private lateinit var buttonUpload: Button
    private lateinit var inputNombre: TextInputLayout
    private lateinit var inputDescripcion: TextInputLayout
    private lateinit var inputPrecio: TextInputLayout
    private lateinit var inputContacto: TextInputLayout
    //private lateinit var inputID: TextInputLayout

    private lateinit var repository: PlaceRepository


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonUpload = view.findViewById(R.id.button_Upload)
        inputNombre = view.findViewById(R.id.inputLayout_new_productFragment_nombre)
        inputDescripcion = view.findViewById(R.id.inputLayout_new_productFragment_descripcion)
        inputPrecio = view.findViewById(R.id.inputLayout_new_productFragment_precio)
        inputContacto = view.findViewById(R.id.inputLayout_new_productFragment_contacto)
        //inputID = view.findViewById(R.id.inputLayout_new_productFragment_ID)

        repository = PlaceRepositoryImpl(
            FirebasePlaceApiImpl(
                Firebase.firestore
            )
        )

        initListeners()

    }

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    private fun initListeners() {
        buttonUpload.setOnClickListener {
            val Nombre = inputNombre.editText!!.text.toString()
            val Descripcion = inputDescripcion.editText!!.text.toString()
            val Precio = inputPrecio.editText!!.text.toString()
            val Contacto = inputContacto.editText!!.text.toString()
            //val id = inputID.editText!!.text.toString().toInt()
            val favorite = false

            val id = rand(1,99)

            lifecycleScope.launch(Dispatchers.IO){
                repository.createPlace(
                    place = Place(
                        id = id,
                        contacto = Contacto,
                        descripcion = Descripcion,
                        nombre = Nombre,
                        precio = Precio,
                        favorite = favorite
                    )
                )

            }
            Toast.makeText(
                requireContext(),
                "Su Producto se ha agregado a Firebase",
                Toast.LENGTH_LONG
            ).show()

        }
    }



}