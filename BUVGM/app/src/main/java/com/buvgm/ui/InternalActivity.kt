package com.buvgm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.buvgm.R
import com.buvgm.ui.SowProducts.ProductsListFragment
import com.buvgm.ui.configuracion.SettingsFragment
import com.buvgm.ui.nuevoproducto.NewProductFragment


class InternalActivity : AppCompatActivity() {

    private lateinit var container: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal)

        container = findViewById(R.id.principal_fragmentContainerView)
        setFragment(ProductsListFragment())

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.principal_fragmentContainerView, fragment).addToBackStack(null)
        }
    }





}