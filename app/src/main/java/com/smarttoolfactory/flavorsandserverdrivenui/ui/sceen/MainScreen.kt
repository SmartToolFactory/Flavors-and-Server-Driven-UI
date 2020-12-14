package com.smarttoolfactory.flavorsandserverdrivenui.ui.sceen

import android.content.Context
import android.content.res.ColorStateList
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.flavorsandserverdrivenui.MainFragmentStateAdapter
import com.smarttoolfactory.flavorsandserverdrivenui.MyApplication
import com.smarttoolfactory.flavorsandserverdrivenui.R
import com.smarttoolfactory.flavorsandserverdrivenui.config.NAVIGATION_TOOLBAR
import com.smarttoolfactory.flavorsandserverdrivenui.ui.fragment.CallsFragment
import com.smarttoolfactory.flavorsandserverdrivenui.ui.fragment.ChatsFragment
import com.smarttoolfactory.flavorsandserverdrivenui.ui.fragment.ContactsFragment

class MainScreen(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle,
    private val inflater: LayoutInflater,
    private val container: ViewGroup?,
    private val context: Context
) {

    // Set idle and selected menu icon colors
    private val colorMap = MyApplication.configManager.colorMap
    private val navTabIconColorSelected =
        colorMap["navTabIconColorSelected"] ?: R.color.nav_tab_icon_color_selected
    private val navTabIconColorIdle =
        colorMap["navTabIconColor"] ?: R.color.nav_tab_icon_color

    private val states = arrayOf(
        intArrayOf(-android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_checked)
    )

    private val colors = intArrayOf(
        navTabIconColorIdle,
        navTabIconColorSelected
    )

    fun getMainScreenView(): View {

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val fragmentList = getFragmentList()
        val viewPager = setUpViewPager(fragmentList, rootView)

        val navigationType =
            MyApplication.configManager.config.navigationType

        if (navigationType == NAVIGATION_TOOLBAR) {
            if (fragmentList.size > 1) {
                createTopNavigation(rootView, inflater, container, viewPager)
            }
        } else {
            if (fragmentList.size > 1) {
                createBottomNavigation(rootView, inflater, container, viewPager)
            }
        }

        return rootView
    }


    /**
     * Get feature fragments based on feature list
     */
    private fun getFragmentList(): MutableList<Fragment> {

        val features = MyApplication.configManager.config.features

        return mutableListOf<Fragment>().apply {

            features.let {
                if (it.featureChat) {
                    add(ChatsFragment())
                }

                if (it.featureContacts) {
                    add(ContactsFragment())
                }

                if (it.featureCall) {
                    add(CallsFragment())
                }
            }
        }
    }

    private fun setUpViewPager(fragmentList: MutableList<Fragment>, rootView: View): ViewPager2 {
        val viewPager = rootView.findViewById<ViewPager2>(R.id.viewPager)

        val adapter = MainFragmentStateAdapter(
            fragmentList,
            fragmentManager,
            lifecycle
        )

        viewPager.adapter = adapter

        val isToolbarNavigation =
            MyApplication.configManager.config.navigationType == NAVIGATION_TOOLBAR

        viewPager.isUserInputEnabled = isToolbarNavigation

        return viewPager
    }

    private fun createTopNavigation(
        rootView: View,
        inflater: LayoutInflater,
        container: ViewGroup?,
        viewPager: ViewPager2
    ) {

        val topContainer = rootView.findViewById<ConstraintLayout>(R.id.containerTop)

        val appbarLayout = inflater.inflate(
            R.layout.layout_app_bar,
            container,
            false
        ) as AppBarLayout

        val toolbar = appbarLayout.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = context.getString(R.string.app_name)

        val features = MyApplication.configManager.config.features

        val tabNames = mutableListOf<String>()
        val icons = mutableListOf<Int>()

        features.let {
            if (it.featureChat) {
                tabNames.add("Chats")
                icons.add(R.drawable.ic_baseline_chat_24)
            }

            if (it.featureContacts) {
                tabNames.add("Contacts")
                icons.add(R.drawable.ic_baseline_contacts_24)
            }

            if (it.featureCall) {
                tabNames.add("Calls")
                icons.add(R.drawable.ic_baseline_call_24)
            }
        }

        val tabLayout = appbarLayout.findViewById<TabLayout>(R.id.tabLayout)

        if (tabNames.size > 1) {

            // Bind tabs and viewpager
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabNames[position]
                tab.icon = ContextCompat.getDrawable(context, icons[position])
            }.attach()
            topContainer.addView(appbarLayout)
        } else {
            tabLayout.visibility = View.GONE
        }

    }

    private fun createBottomNavigation(
        rootView: View,
        inflater: LayoutInflater,
        container: ViewGroup?,
        viewPager: ViewPager2
    ) {
        val bottomContainer = rootView.findViewById<ConstraintLayout>(R.id.containerBottom)

        val bottomNavigationView =
            inflater.inflate(
                R.layout.layout_bottom_navigation_view,
                container,
                false
            ) as BottomNavigationView

        setBottomNavigationViewIcons(bottomNavigationView)

        val menu = bottomNavigationView.menu

        createMenu(menu)

        bottomNavigationView.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.menu_item_chats -> viewPager.currentItem = 0
                R.id.menu_item_contacts -> viewPager.currentItem = 1
                R.id.menu_item_calls -> viewPager.currentItem = 2
            }

            true
        }

        // Add BottomNavigationView to bottom container in this layout
        bottomContainer.addView(bottomNavigationView)
    }

    private fun createMenu(menu: Menu) {
        menu.clear()

        val features = MyApplication.configManager.config.features

        features.let {
            if (it.featureChat) {
                menu.add(
                    Menu.NONE,
                    R.id.menu_item_chats,
                    Menu.NONE,
                    "Chats"
                )
                    .setIcon(R.drawable.ic_baseline_chat_24)
                    .setShowAsAction(
                        MenuItem.SHOW_AS_ACTION_ALWAYS
                    )
            }

            if (it.featureContacts) {
                menu.add(
                    Menu.NONE,
                    R.id.menu_item_contacts,
                    Menu.NONE,
                    "Contacts"
                )
                    .setIcon(R.drawable.ic_baseline_contacts_24)
                    .setShowAsAction(
                        MenuItem.SHOW_AS_ACTION_ALWAYS
                    )
            }

            if (it.featureCall) {
                menu.add(
                    Menu.NONE,
                    R.id.menu_item_calls,
                    Menu.NONE,
                    "Calls"
                )
                    .setIcon(R.drawable.ic_baseline_call_24)
                    .setShowAsAction(
                        MenuItem.SHOW_AS_ACTION_ALWAYS
                    )
            }
        }
    }

    private fun setBottomNavigationViewIcons(bottomNavigationView: BottomNavigationView) {
        val itemIconTintList = ColorStateList(states, colors)
        bottomNavigationView.itemIconTintList = itemIconTintList
    }

}