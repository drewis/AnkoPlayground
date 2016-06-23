package org.opensilk.playground

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
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
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.ankox.colorAttr
import org.jetbrains.ankox.dimenAttr

/**
 * Note: fitsSystemWindows does not work
 */
class MainUI : AnkoComponent<MainActivity> {
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

    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui) {
            drawerLayout {
                lparams(width = matchParent, height = matchParent)
                fitsSystemWindows = true
                setStatusBarBackgroundColor(colorAttr(R.attr.colorPrimaryDark))
                coordinatorLayout {
                    lparams(width = matchParent, height = matchParent)
                    appBarLayout(R.style.AppTheme_AppBarOverlay) {
                        lparams(width = matchParent, height = wrapContent)
                        toolbar {
                            lparams(width = matchParent, height = dimenAttr(android.R.attr.actionBarSize)) {
                                //apply the layout_scrollFlags
                                if (this is AppBarLayout.LayoutParams) {
                                    this.scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_ENTER_ALWAYS
                                }
                            }
                            backgroundColor = colorAttr(R.attr.colorPrimary)
                            popupTheme = R.style.AppTheme_PopupOverlay
                        }.apply {
                            this@MainUI.toolbar = this
                        }
                    }
                    recyclerView {
                        lparams(width = matchParent, height = matchParent) {
                            //apply the scrolling behavior
                            if (this is CoordinatorLayout.LayoutParams) {
                                this.behavior = AppBarLayout.ScrollingViewBehavior()
                            }
                        }
                    }.apply {
                        this@MainUI.recycler = this
                    }
                }.apply {
                    this@MainUI.coordinator = this
                }
                navigationView {
                    lparams(width = wrapContent, height = matchParent) {
                        if (this is DrawerLayout.LayoutParams) {
                            this.gravity = GravityCompat.START;
                        }
                    }
                    fitsSystemWindows = true
                    inflateMenu(R.menu.navigation)
                    inflateHeaderView(R.layout.nav_header)
                }.apply {
                    this@MainUI.navigation = this
                }
            }.apply {
                this@MainUI.drawerLayout = this
            }
        }
    }
}

/**
 * Layout is handled by Anko DSL
 */
class MainActivity : AppCompatActivity() {

    lateinit var mUI : MainUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUI = MainUI()
        mUI.setContentView(this);
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
