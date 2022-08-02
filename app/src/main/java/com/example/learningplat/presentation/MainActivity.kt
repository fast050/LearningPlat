package com.example.learningplat.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration

import androidx.navigation.ui.setupActionBarWithNavController
import com.example.learningplat.R
import com.example.learningplat.data.network.api.CoursesApiService
import com.example.learningplat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    @Inject
    lateinit var api: CoursesApiService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navHostFragment.findNavController()


        setSupportActionBar(binding.mainToolbar)

        //if we use actionbar
        val appBarConfiguration= AppBarConfiguration(navController.graph,binding.drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

//        binding.mainToolbar.setNavigationOnClickListener {
//            binding.drawerLayout.open()
//        }

//        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
//            // Handle menu item selected
//            //menuItem.isChecked = !menuItem.isChecked
//            binding.drawerLayout.close()
//            true
//        }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() ||super.onSupportNavigateUp()
    }
}