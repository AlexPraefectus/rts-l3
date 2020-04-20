package com.example.rst_l3.ui.lab_33

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rst_l3.R

class LAB33Fragment : Fragment() {

    private lateinit var lab33ViewModel: Lab33ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lab33ViewModel =
            ViewModelProviders.of(this).get(Lab33ViewModel::class.java)
        val root = inflater.inflate(R.layout.lab33_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_33)
        lab33ViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
