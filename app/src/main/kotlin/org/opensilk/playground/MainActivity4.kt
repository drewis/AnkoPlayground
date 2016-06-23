package org.opensilk.playground

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import org.opensilk.playground.databinding.ActivityMain4Binding

/**
 * Layout is handled by databinding
 */
class MainActivity4 : AppCompatActivity() {

    val mUI : ActivityMain4Binding by createBinding(R.layout.activity_main4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setup toolbar
        mUI.toolbar.attachToActivity(this, mUI.drawerLayout);
        //setup navigation
        mUI.navView.setNavigationItemSelectedListener {
            goToNavItem(it)
            mUI.navView.setCheckedItem(it.itemId)
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
