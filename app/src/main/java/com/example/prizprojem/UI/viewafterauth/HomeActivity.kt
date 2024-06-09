package com.example.prizprojem.UI.viewafterauth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.prizprojem.R
import com.example.prizprojem.databinding.ActivityHomeBinding
import com.example.prizprojem.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    val navigationItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.menu_home -> {
                val fragment = HomeFragment.Companion.newInstance("","")
                // bu şekilde bir nesnesini oluşturuyoruz
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_add_rules -> {
                val fragment = RulesFragment.Companion.newInstance("","")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }

        }
        return@OnNavigationItemSelectedListener false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.navBottom.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        // Set the initial fragment
        if (savedInstanceState == null) {
            setInitialFragment()
        }

    }// end of the on create
    private fun addFragment(fragment: Fragment) {
        this.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                com.google.android.material.R.anim.design_bottom_sheet_slide_in,
                com.google.android.material.R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.nav_content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    private fun setInitialFragment() {
        val fragment = HomeFragment.newInstance("", "")
        addFragment(fragment)
        binding.navBottom.selectedItemId = R.id.menu_home
        //The line binding.navBottom.selectedItemId = R.id.menu_home sets
        // the selected item in the BottomNavigationView to the "Home" menu item programmatically.
    }
}