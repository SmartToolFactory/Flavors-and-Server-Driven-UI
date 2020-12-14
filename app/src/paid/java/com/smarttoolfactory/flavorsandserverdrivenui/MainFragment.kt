package com.smarttoolfactory.flavorsandserverdrivenui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.smarttoolfactory.flavorsandserverdrivenui.ui.CallsFragment
import com.smarttoolfactory.flavorsandserverdrivenui.ui.ChatsFragment
import com.smarttoolfactory.flavorsandserverdrivenui.ui.ContactsFragment

class MainFragment : Fragment() {

    private val tabNames = listOf("Chats", "Contacts", "Calls")
    private val icons = listOf(
        R.drawable.ic_baseline_chat_24,
        R.drawable.ic_baseline_contacts_24,
        R.drawable.ic_baseline_call_24
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val topContainer = rootView.findViewById<ConstraintLayout>(R.id.containerTop)

        val appbarLayout =
            inflater.inflate(R.layout.layout_app_bar, container, false)

        val toolbar = rootView.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.title = getString(R.string.app_name)

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

        val tabLayout = appbarLayout.findViewById<TabLayout>(R.id.tabLayout)

        // Bind tabs and viewpager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
            tab.icon = ContextCompat.getDrawable(requireContext(), icons[position])
        }.attach()
        topContainer.addView(appbarLayout)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "PAID Fragment", Toast.LENGTH_SHORT).show()
    }
}