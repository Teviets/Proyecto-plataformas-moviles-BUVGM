package com.buvgm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.RenderProcessGoneDetail
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.buvgm.R
import com.buvgm.ui.SowProducts.ProductsListFragment
import com.buvgm.ui.busqueda.SearchFragment
import com.buvgm.ui.busqueda.SearchFragmentDirections
import com.buvgm.ui.configuracion.SettingsFragment
import com.buvgm.ui.favoritos.FavoritesFragment
import com.buvgm.ui.favoritos.FavoritesFragmentDirections
import com.buvgm.ui.login.LoginFragmentDirections
import com.buvgm.ui.nuevoproducto.NewProductFragment
import com.buvgm.ui.productos.ProductsHomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.grpc.Internal
import kotlinx.coroutines.flow.collectLatest

class InternalActivity : AppCompatActivity() {

    private lateinit var tool: androidx.appcompat.widget.Toolbar
    private lateinit var container: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal)

        tool = findViewById(R.id.principal_toolbar)
        container = findViewById(R.id.principal_fragmentContainerView)
        setFragment(ProductsListFragment)
        setListeners()

    }


    private fun setListeners() {
        tool.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.nuevoProducto_item -> {
                    tool.visibility = View.GONE
                    setFragment(NewProductFragment())
                    true
                }
                R.id.settings_item -> {
                    tool.visibility = GONE

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

    override fun onBackPressed() {
        tool.visibility = VISIBLE
        super.onBackPressed()
    }




}