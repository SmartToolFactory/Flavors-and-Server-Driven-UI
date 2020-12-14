package com.smarttoolfactory.flavorsandserverdrivenui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarttoolfactory.flavorsandserverdrivenui.ui.CallsFragment
import com.smarttoolfactory.flavorsandserverdrivenui.ui.ChatsFragment
import com.smarttoolfactory.flavorsandserverdrivenui.ui.ContactsFragment

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val fragmentList = getFragmentList()

        val viewPager = setUpViewPager(fragmentList, rootView)

        if (fragmentList.size > 1) {
            createBottomNavigation(rootView, inflater, container, viewPager)
        }

        return rootView
    }

    private fun getFragmentList(): MutableList<Fragment> {

        val features = MyApplication.configManager.config?.features

        return mutableListOf<Fragment>().apply {

            features?.let {
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
            childFragmentManager,
            viewLifecycleOwner.lifecycle
        )

        viewPager.adapter = adapter

        viewPager.isUserInputEnabled = false
        return viewPager
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

        menu.clear()

        val features = MyApplication.configManager.config?.features

        features?.let {
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

    private fun setBottomNavigationViewIcons(bottomNavigationView: BottomNavigationView) {
        // Set idle and selected BottomNavigationView icon colors
        val colorMap = MyApplication.configManager.colorMap
        val navTabIconColorSelected =
            colorMap["navTabIconColorSelected"] ?: R.color.nav_tab_icon_color_selected
        val navTabIconColorIdle = colorMap["navTabIconColor"] ?: R.color.nav_tab_icon_color_selected

        val states = arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_checked)
        )

        val colors = intArrayOf(
            navTabIconColorIdle,
            navTabIconColorSelected
        )

        val itemIconTintList = ColorStateList(states, colors)
        bottomNavigationView.itemIconTintList = itemIconTintList
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "FREE Fragment", Toast.LENGTH_SHORT).show()
    }
}