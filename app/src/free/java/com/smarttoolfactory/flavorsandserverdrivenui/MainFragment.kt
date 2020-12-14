package com.smarttoolfactory.flavorsandserverdrivenui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val bottomContainer = rootView.findViewById<ConstraintLayout>(R.id.containerBottom)

        val bottomNavigationView =
            inflater.inflate(R.layout.layout_bottom_navigation_view, container, false)

        bottomContainer.addView(bottomNavigationView)

        val viewPager = rootView.findViewById<ViewPager2>(R.id.viewPager)

        val fragmentList = mutableListOf<Fragment>().apply {
            add(ChatsFragment())
            add(ContactsFragment())
            add(CallsFragment())
        }

        val adapter = MainFragmentStateAdapter(
            fragmentList,
            childFragmentManager,
            viewLifecycleOwner.lifecycle
        )

        viewPager.adapter = adapter

        viewPager.isUserInputEnabled = false

        val bottomNavigation =
            rootView.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.menu_item_chats -> viewPager.currentItem = 0
                R.id.menu_item_contacts -> viewPager.currentItem = 1
                R.id.menu_item_calls -> viewPager.currentItem = 2
            }

            true
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "FREE Fragment", Toast.LENGTH_SHORT).show()
    }
}