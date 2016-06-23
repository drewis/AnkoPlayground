package org.opensilk.playground

import android.app.TaskStackBuilder
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Handler
import android.support.annotation.LayoutRes
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem

fun Toolbar.attachToActivity(activity: AppCompatActivity, drawerLayout: DrawerLayout) {
    activity.setSupportActionBar(this);
    val abt = ActionBarDrawerToggle(activity, drawerLayout, this,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawerLayout.addDrawerListener(abt)
    abt.syncState()
}

fun CoordinatorLayout.showShortSnackbarMsg(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun genItems(): Array<String> {
    var items = emptyArray<String>()
    for (ii in 1..100) {
        items += "Item $ii"
    }
    return items
}

fun <T : ViewDataBinding> AppCompatActivity.createBinding(@LayoutRes layout: Int): Lazy<T> {
    return lazy {
        DataBindingUtil.setContentView<T>(this, layout)
    }
}

fun AppCompatActivity.goToNavItem(item: MenuItem) {
    Handler().postDelayed({ goToScreenFromNavItem(this, item) }, 250)
}

private fun goToScreenFromNavItem(activity: AppCompatActivity, menuItem: MenuItem) {
    when (menuItem.itemId) {
        R.id.nav_one -> {
            val bob = TaskStackBuilder.create(activity)
            bob.addNextIntentWithParentStack(Intent(activity, MainActivity::class.java))
            bob.startActivities()
        }
        R.id.nav_two -> {
            val bob = TaskStackBuilder.create(activity)
            bob.addNextIntentWithParentStack(Intent(activity, MainActivity2::class.java))
            bob.startActivities()
        }
        R.id.nav_three -> {
            val bob = TaskStackBuilder.create(activity)
            bob.addNextIntentWithParentStack(Intent(activity, MainActivity3::class.java))
            bob.startActivities()
        }
        R.id.nav_four -> {
            val bob = TaskStackBuilder.create(activity)
            bob.addNextIntentWithParentStack(Intent(activity, MainActivity4::class.java))
            bob.startActivities()
        }
    }
}