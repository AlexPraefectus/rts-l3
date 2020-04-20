package com.example.rst_l3.ui.lab_31

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rst_l3.R

class LAB31Fragment : Fragment() {

    private lateinit var lab31ViewModel: LAB31ViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        lab31ViewModel =
                ViewModelProviders.of(this).get(LAB31ViewModel::class.java)
        val root = inflater.inflate(R.layout.lab31_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        lab31ViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val button: Button = root.findViewById(R.id.l31_factorise_btn)
        button.setOnClickListener {

        }
        return root

    }
}
