package com.example.rst_l3.ui.lab_32

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rst_l3.R

class LAB32Fragment : Fragment() {

    private lateinit var lab32ViewModel: LAB32ViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lab32ViewModel =
                ViewModelProviders.of(this).get(LAB32ViewModel::class.java)
        val root = inflater.inflate(R.layout.lab32_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        lab32ViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
