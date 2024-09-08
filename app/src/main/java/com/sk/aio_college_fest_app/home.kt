package com.sk.aio_college_fest_app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView

class home : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val imageList = listOf(
        R.drawable.banner,
        R.drawable.banner,
        R.drawable.banner
    )
    private val scrollInterval: Long = 3000 // Interval in milliseconds (3 seconds)

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)


        drawerLayout = findViewById(R.id.drawer_layout)

        findViewById<ImageButton>(R.id.navButton).setOnClickListener{
            drawerLayout.open()
        }

        navView = findViewById(R.id.nav_view)

        // Set up the navigation drawer
        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Handle home click
                }

                R.id.nav_settings -> {
                    // Handle settings click
                }

                R.id.nav_about -> {
                    // Handle about click
                }
            }

            // Close the drawer when an item is selected
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        })


        viewPager = findViewById(R.id.viewPager)

        val adapter = CarouselAdapter(imageList)
        viewPager.adapter = adapter

        // Initialize Handler and Runnable
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                val currentItem = viewPager.currentItem
                val nextItem = (currentItem + 1) % imageList.size
                viewPager.setCurrentItem(nextItem, true)
                handler.postDelayed(this, scrollInterval)
            }
        }

        // Start auto-scrolling
        handler.postDelayed(runnable, scrollInterval)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Create a list of items (image resource ID and text)
        val items = listOf(
            Pair(R.drawable.standup, "Standup"),
            Pair(R.drawable.workshops, "Workshops"),
            Pair(R.drawable.book, "Books"),
            Pair(R.drawable.dj, "DJ Night"),
            Pair(R.drawable.interview, "Interviews"),
            Pair(R.drawable.music, "Music"),
            Pair(R.drawable.treasurehunt, "Treasure Hunt"),

        )

        // Set up the adapter
        val adapter1 = CardAdapter(items)
        recyclerView.adapter = adapter1

        // Configure RecyclerView for horizontal scrolling
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }
}