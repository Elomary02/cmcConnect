package com.example.cmcconnect

import android.os.Bundle
import android.view.View
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
import com.example.cmcconnect.model.UserInInfo
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
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

        when(UserInInfo.id_type_user_fk){
            1-> {
                navigationView.inflateMenu(R.menu.nav_menu)
                appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.id_dashboardFragment,
                        R.id.id_resourcesFragment, R.id.id_requestsFragment, R.id.id_justifFragment,
                        R.id.id_chatHomeFragment, R.id.id_eventsFragment, R.id.id_profileFragment
                    ),
                    drawerLayout
                )

            }
            2->{
                navigationView.inflateMenu(R.menu.teacher_menu)
                appBarConfiguration = AppBarConfiguration(
                    setOf(
                        R.id.id_dashboardFragment,
                        R.id.id_seeResourcesFragment, R.id.id_postResourcesFragment, R.id.id_requestsFragment,
                        R.id.id_groupsFragment, R.id.id_chatHomeFragment, R.id.id_eventsFragment, R.id.id_profileFragment
                    ),
                    drawerLayout
                )


            }
        }

        navController = findNavController(R.id.fragmentContainerView)

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
        val toolbar : Toolbar = findViewById(R.id.myToolBar)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
            when(destination.id){
                R.id.id_groupeStudentsFragment , R.id.eventsDetailsFragment -> toolbar.visibility = View.GONE
                else -> toolbar.visibility = View.VISIBLE
            }
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