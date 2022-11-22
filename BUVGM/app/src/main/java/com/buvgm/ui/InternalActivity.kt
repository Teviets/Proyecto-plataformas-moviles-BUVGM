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
import com.buvgm.ui.busqueda.SearchFragment
import com.buvgm.ui.busqueda.SearchFragmentDirections
import com.buvgm.ui.configuracion.SettingsFragment
import com.buvgm.ui.favoritos.FavoritesFragment
import com.buvgm.ui.favoritos.FavoritesFragmentDirections
import com.buvgm.ui.login.LoginFragmentDirections
import com.buvgm.ui.nuevoproducto.NewProductFragment
import com.buvgm.ui.productos.ProductsHomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest

class InternalActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var tool: androidx.appcompat.widget.Toolbar
    private lateinit var container: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal)

        bottomNav = findViewById(R.id.bottomNavigationView)
        tool = findViewById(R.id.principal_toolbar)
        container = findViewById(R.id.principal_fragmentContainerView)

        setListeners()

    }


    private fun setListeners() {
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home_item -> setFragment(ProductsHomeFragment())
                R.id.search_item -> setFragment(SearchFragment())
                R.id.favorites_item -> setFragment(FavoritesFragment())
                else -> setFragment(ProductsHomeFragment())

            }
            true
        }
        tool.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.nuevoProducto_item -> {
                    tool.visibility = View.GONE
                    bottomNav.visibility = View.GONE
                    setFragment(NewProductFragment())
                    true
                }
                R.id.settings_item -> {
                    tool.visibility = GONE
                    bottomNav.visibility = GONE
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
        bottomNav.visibility = View.VISIBLE
        super.onBackPressed()
    }


}