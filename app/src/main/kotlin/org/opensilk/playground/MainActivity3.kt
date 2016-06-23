package org.opensilk.playground

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Layout inflated the normal way through setContentView
 * Uses android-extensions to access layout components
 */
class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup toolbar
        toolbar.attachToActivity(this, drawerLayout);
        //setup navigation
        navView.setNavigationItemSelectedListener {
            goToNavItem(it)
            navView.setCheckedItem(it.itemId)
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
