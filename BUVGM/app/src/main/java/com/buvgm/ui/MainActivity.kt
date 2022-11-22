package com.buvgm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.buvgm.R
import com.buvgm.databinding.ActivityMainBinding
import com.buvgm.ui.SowProducts.ProductsListFragment
import com.buvgm.ui.login.LoginFragment
import com.buvgm.ui.registro.NewAccountFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}