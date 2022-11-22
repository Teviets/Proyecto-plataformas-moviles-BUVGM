package com.buvgm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.buvgm.R
import com.buvgm.ui.SowProducts.ProductsListFragment
import com.buvgm.ui.nuevoproducto.NewProductFragment
import com.buvgm.ui.settings.SettingsFragment


class InternalActivity : AppCompatActivity() {

    private lateinit var container: FragmentContainerView
    private lateinit var tool: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal)

        container = findViewById(R.id.principal_fragmentContainerView)
        tool = findViewById(R.id.principal_toolbar)
        setFragment(ProductsListFragment())

        setListeners()

    }

    private fun setListeners(){
        tool.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.nuevoProducto_item -> {
                    tool.visibility = View.GONE
                    setFragment(NewProductFragment())
                    true
                }
                R.id.settings_item -> {
                    tool.visibility = View.GONE
                    setFragment(SettingsFragment())

                    true
                }
                else -> true
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.principal_fragmentContainerView, fragment).addToBackStack(null)
        }
    }


}