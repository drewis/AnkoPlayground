package org.opensilk.playground

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.find

class MainActivity3 : AppCompatActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var coordinator : CoordinatorLayout
    lateinit var toolbar : Toolbar
    lateinit var recycler : RecyclerView
    lateinit var navigation : NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = find(R.id.drawer_layout)
        coordinator = find(R.id.coordinator)
        toolbar = find(R.id.toolbar)
        recycler = find(R.id.recycler)
        navigation = find(R.id.nav_view)

        //setup toolbar
        toolbar.attachToActivity(this, drawerLayout);
        //setup navigation
        navigation.setNavigationItemSelectedListener {
            goToNavItem(it)
            navigation.setCheckedItem(it.itemId)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        //setup recycler
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = SimpleRecyclerAdapter(genItems())
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed()
        }
    }

}
