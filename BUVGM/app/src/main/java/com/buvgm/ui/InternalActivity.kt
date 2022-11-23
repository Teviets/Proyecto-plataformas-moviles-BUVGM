package com.buvgm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    private lateinit var fagmentContaine: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal)

        container = findViewById(R.id.FragmentContainerView_Internal)
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
            replace(R.id.FragmentContainerView_Internal, fragment).addToBackStack(null)
        }
    }

    override fun onBackPressed() {
        tool.visibility= View.VISIBLE
        super.onBackPressed()
    }

}