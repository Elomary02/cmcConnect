package com.example.cmcconnect

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView : NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var menuCloseBtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.myToolBar)
        setSupportActionBar(toolbar)


        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.id_dashboardFragment,
            R.id.id_resourcesFragment,R.id.id_requestsFragment,R.id.id_justifFragment,
            R.id.id_chatHomeFragment,R.id.id_eventsFragment,R.id.id_profileFragment))
        setupActionBarWithNavController(navController, drawerLayout)
        navigationView.setupWithNavController(navController)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)

        val customBurgerIcon: ImageButton = findViewById(R.id.customBurgerIcon)
        val headerView = navigationView.getHeaderView(0)
        menuCloseBtn = headerView.findViewById(R.id.menuCloseBtn)
        menuCloseBtn.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        customBurgerIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            menuItem.onNavDestinationSelected(navController)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}