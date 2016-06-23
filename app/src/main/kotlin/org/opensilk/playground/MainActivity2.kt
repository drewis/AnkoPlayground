package org.opensilk.playground

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import org.jetbrains.anko.*

class Main2UI : AnkoComponent<MainActivity2> {
    internal lateinit var drawerLayout : DrawerLayout
        private set
    internal lateinit var coordinator : CoordinatorLayout
        private set
    internal lateinit var toolbar : Toolbar
        private set
    internal lateinit var recycler : RecyclerView
        private set
    internal lateinit var navigation : NavigationView
        private set

    override fun createView(ui: AnkoContext<MainActivity2>): View {
        return with(ui) {
            include<DrawerLayout>(R.layout.activity_main).apply {
                drawerLayout = find(R.id.drawer_layout)
                coordinator = find(R.id.coordinator)
                toolbar = find(R.id.toolbar)
                recycler = find(R.id.recycler)
                navigation = find(R.id.nav_view)
            }
        }
    }
}

class MainActivity2 : AppCompatActivity() {

    lateinit var mUI : Main2UI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUI = Main2UI()
        mUI.setContentView(this)

        //setup toolbar
        mUI.toolbar.attachToActivity(this, mUI.drawerLayout);
        //setup navigation
        mUI.navigation.setNavigationItemSelectedListener {
            goToNavItem(it)
            mUI.navigation.setCheckedItem(it.itemId)
            mUI.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        //setup recycler
        mUI.recycler.layoutManager = LinearLayoutManager(this)
        mUI.recycler.adapter = SimpleRecyclerAdapter(genItems())
    }

    override fun onBackPressed() {
        if (mUI.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mUI.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed()
        }
    }

}
