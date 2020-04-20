package com.example.rst_l3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rst_l3.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val textView: TextView = root.findViewById(R.id.home_header_1)
        homeViewModel.header1.observe(viewLifecycleOwner, Observer { textView.text = it })
        val textView2: TextView = root.findViewById(R.id.home_header_2)
        homeViewModel.header2.observe(viewLifecycleOwner, Observer { textView2.text = it })
        val textView3: TextView = root.findViewById(R.id.home_header_3)
        homeViewModel.header3.observe(viewLifecycleOwner, Observer { textView3.text = it })

        return root
    }
}
