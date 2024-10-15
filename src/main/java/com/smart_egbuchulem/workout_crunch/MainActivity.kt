//package com.smart_egbuchulem.workout_crunch
//
//import android.os.Build
//import android.os.Bundle
//import android.view.MenuItem
//import android.widget.Toast
//import android.window.OnBackInvokedDispatcher

//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.GravityCompat
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentTransaction
//import com.google.android.material.navigation.NavigationView
//import com.smart_egbuchulem.workout_crunch.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//
//    private lateinit var fragmentManager: FragmentManager
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        // Drawer Toggle Setup
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.toolbar,
//            R.string.nav_open,
//            R.string.nav_close
//        )
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        // Set up navigation drawer item click listener
//        binding.navigationDrawer.setNavigationItemSelectedListener(this)
//
//        // Bottom Navigation listener
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.bottom_home -> openFragment(HomeFragment())
//                R.id.bottom_profile -> openFragment(ProfileFragment())
//                R.id.bottom_cart -> openFragment(CartFragment())
//                R.id.bottom_menu -> openFragment(MenuFragment())
//                else -> false
//            }
//            true
//        }
//
//        // Initialize fragment manager and open default fragment
//        fragmentManager = supportFragmentManager
//        openFragment(HomeFragment())
//
//        // FAB Click listener
//        binding.fab.setOnClickListener {
//            Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    // Handle navigation drawer item selection
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_prime -> openFragment(PrimeFragment())
//            R.id.nav_fashion -> openFragment(FashionFragment())
//            R.id.nav_electronics -> openFragment(ElectronicsFragment())
////            R.id.nav_fresh -> openFragment(FreshFragment())
////            R.id.nav_beauty -> openFragment(BeautyFragment())
////            R.id.nav_furniture -> openFragment(FunitureFragment())
//            else -> return false
//        }
//
//        // Close the drawer after an item is selected
//        binding.drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//
//    override fun onBackPressed() {
//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                // For API level 33 (Tiramisu) and above
//                onBackInvokedDispatcher.registerOnBackInvokedCallback(
//                    OnBackInvokedDispatcher.PRIORITY_DEFAULT
//                ) {
//                    super.onBackPressedDispatcher.onBackPressed()
//                }
//            } else {
//                // For API levels below 33 (Pre-Tiramisu)
//                super.onBackPressed()
//            }
//        }
//    }
//
//
//    // Helper function to open fragments
//    private fun openFragment(fragment: Fragment) {
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment_container, fragment)
//        fragmentTransaction.addToBackStack(null) // Adds to backstack to handle back navigation between fragments
//        fragmentTransaction.commit()
//    }
//}




//package com.smart_egbuchulem.workout_crunch
//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import android.view.MenuItem
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.GravityCompat
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.material.navigation.NavigationView
//import com.google.firebase.auth.FirebaseAuth
//import com.smart_egbuchulem.workout_crunch.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var googleSignInClient: GoogleSignInClient
//    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        setupDrawer()
//        setupGoogleSignInClient()
//
//        binding.navigationDrawer.setNavigationItemSelectedListener(this)
//
//        // Open default fragment
//        openFragment(HomeFragment())
//    }
//
//    // Initialize Google Sign-In Client
//    private fun setupGoogleSignInClient() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        googleSignInClient = GoogleSignIn.getClient(this, gso)
//    }
//
//    // Drawer setup
//    private fun setupDrawer() {
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.toolbar,
//            R.string.nav_open,
//            R.string.nav_close
//        )
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_prime -> openFragment(PrimeFragment())
//            R.id.nav_fashion -> openFragment(FashionFragment())
//            R.id.nav_electronics -> openFragment(ElectronicsFragment())
//            R.id.nav_sign_out -> showSignOutDialog()
//            else -> return false
//        }
//
//        binding.drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//    // Show sign-out confirmation dialog
//    private fun showSignOutDialog() {
//        AlertDialog.Builder(this)
//            .setTitle("Sign Out")
//            .setMessage("Are you sure you want to sign out?")
//            .setPositiveButton("Yes") { _, _ -> signOut() }
//            .setNegativeButton("No", null)
//            .show()
//    }
//
//    private fun signOut() {
//        googleSignInClient.signOut().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                firebaseAuth.signOut()
//                Log.d("MainActivity", "Sign-out successful")
//                startActivity(Intent(this, SignUpActivity::class.java)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
//                finish()
//            } else {
//                Log.e("MainActivity", "Sign-out failed", task.exception)
//            }
//        }
//    }
//
//    // Back press logic for newer and older Android versions
//    override fun onBackPressed() {
//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                onBackInvokedDispatcher.registerOnBackInvokedCallback(
//                    OnBackInvokedDispatcher.PRIORITY_DEFAULT
//                ) {
//                    finish()
//                }
//            } else {
//                super.onBackPressed()
//            }
//        }
//    }
//
//    // Function to open a fragment
//    private fun openFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, fragment)
//            .addToBackStack(null)
//            .commitAllowingStateLoss()
//    }
//}



//package com.smart_egbuchulem.workout_crunch
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.view.MenuItem
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.GravityCompat
//import coil.load
//import androidx.appcompat.app.ActionBarDrawerToggle
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.auth.FirebaseAuth
//import com.smart_egbuchulem.workout_crunch.databinding.ActivityMainBinding
//import com.smart_egbuchulem.workout_crunch.databinding.NavHeaderBinding
//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var headerBinding: NavHeaderBinding
//    private lateinit var googleSignInClient: GoogleSignInClient
//    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//        setupDrawer()
//        setupGoogleSignInClient()
//        setupUserDetails()
//    }
//
//    private fun setupDrawer() {
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.toolbar,
//            R.string.nav_open,
//            R.string.nav_close
//        )
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        val navigationView = binding.navigationDrawer
//        headerBinding = NavHeaderBinding.bind(navigationView.getHeaderView(0))
//
//        navigationView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.nav_sign_out -> {
//                    showSignOutDialog()
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//
//    private fun setupGoogleSignInClient() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        googleSignInClient = GoogleSignIn.getClient(this, gso)
//    }
//
//    private fun setupUserDetails() {
//        val user = firebaseAuth.currentUser
//        user?.let {
//            headerBinding.userNameTextView.text = it.displayName ?: "User"
//            headerBinding.userEmailTextView.text = it.email ?: "Email not available"
//            headerBinding.profileImageView.load(it.photoUrl) {
//                placeholder(R.drawable.baseline_person_24)
//                error(R.drawable.baseline_error_24)
//            }
//        }
//    }
//
//    private fun showSignOutDialog() {
//        AlertDialog.Builder(this)
//            .setTitle("Sign Out")
//            .setMessage("Are you sure you want to sign out?")
//            .setPositiveButton("Yes") { _, _ -> signOut() }
//            .setNegativeButton("No", null)
//            .show()
//    }
//
//    private fun signOut() {
//        googleSignInClient.signOut().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                firebaseAuth.signOut()
//                val intent = Intent(this, SignUpActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
//                finish()
//            } else {
//                Log.e("MainActivity", "Sign-out failed", task.exception)
//            }
//        }
//    }
//}

//
//package com.smart_egbuchulem.workout_crunch
//
//import android.os.Build
//import android.os.Bundle
//import android.view.MenuItem
//import android.widget.Toast
//import android.window.OnBackInvokedDispatcher
//import androidx.appcompat.app.ActionBarDrawerToggle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.GravityCompat
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentTransaction
//import com.google.android.material.navigation.NavigationView
//import com.smart_egbuchulem.workout_crunch.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//
//    private lateinit var fragmentManager: FragmentManager
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        // Drawer Toggle Setup
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.toolbar,
//            R.string.nav_open,
//            R.string.nav_close
//        )
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        // Set up navigation drawer item click listener
//        binding.navigationDrawer.setNavigationItemSelectedListener(this)
//
//        // Bottom Navigation listener
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.bottom_home -> openFragment(HomeFragment())
//                R.id.bottom_profile -> openFragment(ProfileFragment())
//                R.id.bottom_cart -> openFragment(CartFragment())
//                R.id.bottom_menu -> openFragment(MenuFragment())
//                else -> false
//            }
//            true
//        }
//
//        // Initialize fragment manager and open default fragment
//        fragmentManager = supportFragmentManager
//        openFragment(HomeFragment())
//
//        // FAB Click listener
//        binding.fab.setOnClickListener {
//            Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    // Handle navigation drawer item selection
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_prime -> openFragment(PrimeFragment())
//            R.id.nav_fashion -> openFragment(FashionFragment())
//            R.id.nav_electronics -> openFragment(ElectronicsFragment())
////            R.id.nav_fresh -> openFragment(FreshFragment())
////            R.id.nav_beauty -> openFragment(BeautyFragment())
////            R.id.nav_furniture -> openFragment(FunitureFragment())
//            else -> return false
//        }
//
//        // Close the drawer after an item is selected
//        binding.drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
//
//
//    override fun onBackPressed() {
//        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            binding.drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                // For API level 33 (Tiramisu) and above
//                onBackInvokedDispatcher.registerOnBackInvokedCallback(
//                    OnBackInvokedDispatcher.PRIORITY_DEFAULT
//                ) {
//                    super.onBackPressedDispatcher.onBackPressed()
//                }
//            } else {
//                // For API levels below 33 (Pre-Tiramisu)
//                super.onBackPressed()
//            }
//        }
//    }
//
//
//    // Helper function to open fragments
//    private fun openFragment(fragment: Fragment) {
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment_container, fragment)
//        fragmentTransaction.addToBackStack(null) // Adds to backstack to handle back navigation between fragments
//        fragmentTransaction.commit()
//    }
//}
//


package com.smart_egbuchulem.workout_crunch

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.smart_egbuchulem.workout_crunch.databinding.ActivityMainBinding
import com.smart_egbuchulem.workout_crunch.databinding.NavHeaderBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NavHeaderBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupDrawer()
        setupGoogleSignInClient()
        setupUserDetails()

        // Bottom Navigation listener
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_profile -> openFragment(ProfileFragment())
                R.id.bottom_cart -> openFragment(CartFragment())
                R.id.bottom_menu -> openFragment(MenuFragment())
                else -> false
            }
            true
        }

        // FloatingActionButton Click listener
        binding.fab.setOnClickListener {
            Toast.makeText(this, "Categories", Toast.LENGTH_SHORT).show()
        }

        // Initialize fragment manager and open default fragment
        openFragment(HomeFragment())
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = binding.navigationDrawer
        headerBinding = NavHeaderBinding.bind(navigationView.getHeaderView(0))

        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupGoogleSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun setupUserDetails() {
        val user = firebaseAuth.currentUser
        user?.let {
            headerBinding.userNameTextView.text = it.displayName ?: "User"
            headerBinding.userEmailTextView.text = it.email ?: "Email not available"
            headerBinding.profileImageView.load(it.photoUrl) {
                placeholder(R.drawable.baseline_person_24)
                error(R.drawable.baseline_error_24)
                transformations(CircleCropTransformation())
            }
            headerBinding.profileImageView.setOnClickListener {
                // Navigate to NextActivity when profile image is clicked
                startActivity(Intent(this, NextActivity::class.java))
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_prime -> openFragment(PrimeFragment())
            R.id.nav_fashion -> openFragment(FashionFragment())
            R.id.nav_electronics -> openFragment(ElectronicsFragment())
            R.id.nav_sign_out -> {
                showSignOutDialog()
                return true
            }
            else -> return false
        }

        // Close the drawer after an item is selected
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showSignOutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Sign Out")
            .setMessage("Are you sure you want to sign out?")
            .setPositiveButton("Yes") { _, _ -> signOut() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun signOut() {
        googleSignInClient.signOut().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseAuth.signOut()
                val intent = Intent(this, SignUpActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                Log.e("MainActivity", "Sign-out failed", task.exception)
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
